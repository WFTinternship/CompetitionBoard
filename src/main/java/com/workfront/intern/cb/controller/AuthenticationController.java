package com.workfront.intern.cb.controller;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.common.util.StringHelper;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.web.Initializer;
import com.workfront.intern.cb.web.util.Helpers;
import com.workfront.intern.cb.web.util.Params;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class AuthenticationController {
    private static Logger LOG = Logger.getLogger(AuthenticationController.class);

    @Autowired
    private ManagerService managerService;

    // region <SIGN-UP>

    @RequestMapping(value = {"/signup-page"})
    public String toSignUpPage(Model model, HttpServletResponse response) {

        return Params.PAGE_SIGN_UP;
    }

    @RequestMapping(value = "/signup-form", method = RequestMethod.POST)
    public String signUp(Model model, HttpServletRequest request, HttpServletResponse response) {

        // parsing request
        String username = null;
        String password = null;
        String confirmPassword = null;
        File imageFile = null;
        File avatar = null;

        HttpSession session = request.getSession();

        // validating request multipart type
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            String errorMessages = "Invalid custom request was received for Post creation operation: FORM_NOT_MULTIPART";
            LOG.warn(errorMessages);
            // TODO:
        }

        // creating a new file upload handler
        FileItemFactory fiFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fiFactory);
        upload.setHeaderEncoding("UTF-8");

        try {
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                // if not a file
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    switch (fieldName) {
                        case "userNameSignIn":
                            username = item.getString("UTF-8");
                            break;
                        case "passwordSignIn":
                            password = item.getString("UTF-8");
                            break;
                        case "passwordConfirmSignIn":
                            confirmPassword = item.getString("UTF-8");
                            break;
                        default:
                            throw new RuntimeException(String.format("unknown parameter received: %s", fieldName));
                    }
                }
                // is file
                else {
                    String path = Initializer.tempDir.getAbsolutePath();
                    File userFilesDir = new File(path);
                    if (!userFilesDir.exists() && !userFilesDir.mkdirs()) {
                        LOG.warn(String.format("unable to create file: %s", path));
                    }

                    // setting original file name as image name and generating unique file name
                    String fileName = path + "/image_" + System.currentTimeMillis() +
                            "." + Helpers.getFileExtension(new File(item.getName()));

                    imageFile = new File(fileName);
                    if (imageFile.exists() && imageFile.isFile()) {
                        String newFileName = Helpers.generateFileName(imageFile);
                        imageFile = new File(newFileName);
                    }

                    // validating field name
                    String fieldName = item.getFieldName();
                    if (!fieldName.equals("userAvatar")) {
                        //TODO:
                    }

                    // writing file to disk
                    if (imageFile.getName().length() > 0 && !imageFile.exists()) {
                        item.write(imageFile);
                        String destination = Initializer.getResourcesPath() + File.separator
                                + "img/user_avatar/" + imageFile.getName();
                        avatar = new File(destination);
                        FileUtils.moveFile(imageFile, avatar);
                    }
                }
            }
        } catch (FileUploadException ex) {
            String errMsg = "Error occurred while uploading file: " + ex.getMessage();
            LOG.error(errMsg, ex);
            session.setAttribute("errMessage", errMsg);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            String errMsg = "Exception was thrown while adding a new post: " + ex.getMessage();
            session.setAttribute("errMessage", errMsg);
        }

        // validating passwords
        if (password != null && !password.equals(confirmPassword)) {
            String passErrMsg = "Password does not match";
            model.addAttribute("passwordNotMatchErr", passErrMsg);

            return "secure/sign-up";
        }

        try {
            // initializing result object
            Manager manager = new Manager();
            manager.setLogin(username);
            manager.setPassword(password);
            if (avatar != null) {
                manager.setAvatar(avatar.getName());
            }
            // set avatar size
            int scaledWidth = 40;
            int scaledHeight = 40;
            if (avatar != null) {
                Helpers.imageResizeAndWriteToSpecificFolder(avatar.getAbsolutePath(), scaledWidth, scaledHeight);
            }
            // save manager instance to DB
            managerService.addManager(manager);

            // get added manager and set in session
            manager = managerService.getManagerByLogin(username);
            session.setAttribute("manager", manager);

        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);

        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);

            // Checking duplicate of manager name during registration
            String duplicateUserErrMsg = "Sorry, but user with this name exists";
            session.setAttribute("duplicateUserErrMsg", duplicateUserErrMsg);

            return "redirect:signup-page";
        }

        return "redirect:/";
    }
    // endregion

    // region <LOG-IN>

    @RequestMapping(value = {"/login-page"})
    public String toLogIinPage() {
        return Params.PAGE_LOG_IN;
    }

    @RequestMapping(value = "/login-form", method = RequestMethod.POST)
    public String logIn(@RequestParam("usernameLogin") String loginInput,
                        @RequestParam("passwordLogin") String passwordInput,
                        HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (loginInput != null && passwordInput != null) {
            String userNameErrMsg = "Sorry, username or password error";

            // Encrypted input password
            String passwordEncrypt = StringHelper.passToEncrypt(passwordInput);

            // Check valid login and password.
            // For invalid login and password sends error messages.
            try {
                Manager manager = managerService.getManagerByLogin(loginInput);
                String loginFromDb = manager.getLogin();
                String passwordFromDb = manager.getPassword();

                // Check login and password for LogIn system
                if (loginInput.equals(loginFromDb) && passwordEncrypt.equals(passwordFromDb)) {
                    session.setAttribute("manager", manager);
                } else {
                    session.setAttribute("userNameErr", userNameErrMsg);
                    return "redirect:/login-page";
                }
            } catch (Exception ex) {
                session.setAttribute("userNameErr", userNameErrMsg);
                return "redirect:/login-page";
            }
        }
        return "redirect:/";
    }

    // endregion

    // region <LOG-OUT>

    @RequestMapping(value = {"/logout-page"})
    public String toLogOutPage(Model model, HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");

        HttpSession session = request.getSession();

        if (session != null) {
//            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//            response.setDateHeader("Expires", 0);
            session.setAttribute("manager", null);
            session.invalidate();
        }
        return "redirect:/";
    }
    // endregion
}