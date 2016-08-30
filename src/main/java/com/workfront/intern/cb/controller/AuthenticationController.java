package com.workfront.intern.cb.controller;

import com.workfront.intern.cb.common.Manager;
import com.workfront.intern.cb.service.ManagerService;
import com.workfront.intern.cb.web.Initializer;
import com.workfront.intern.cb.web.util.FileHelper;
import com.workfront.intern.cb.web.util.ImageHelper;
import com.workfront.intern.cb.web.util.Params;
import com.workfront.intern.cb.web.util.Util;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
import java.util.List;

@Controller
public class AuthenticationController {
    private static Logger LOG = Logger.getLogger(AuthenticationController.class);

    @Autowired
    private ManagerService managerService;

    // region <SIGN-UP CASES>

    @RequestMapping("/signup-page")
    public String toSignUpPage() {
        return Params.PAGE_SIGN_UP;
    }

    @RequestMapping(value = "/signup-form", method = RequestMethod.POST)
    public String signUp(Model model,
                         @RequestParam("userNameSignIn") String signInLoginInput,
                         @RequestParam("passwordSignIn") String passwordSignInInput,
                         @RequestParam("passwordConfirmSignIn") String passwordConfirmSignIn,
                         HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        // validating request multipart type
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            String errorMessages = "Invalid custom request was received for Post creation operation: FORM_NOT_MULTIPART";
            LOG.warn(errorMessages);
            // TODO: poslat na...
        }

        // creating a new file upload handler
        FileItemFactory fiFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fiFactory);
        upload.setHeaderEncoding("UTF-8");

        // parsing request
        String username = null;
        String password = null;
        String confirmPassword = null;
        File imageFile = null;

        try {
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                if (item.isFormField()) { // if not a file
                    String fieldName = item.getFieldName();

                    if (fieldName.equals("userNameSignIn")) {
                        username = item.getString("UTF-8");
                    } else if (fieldName.equals("passwordSignIn")) {
                        password = item.getString("UTF-8");
                    } else if (fieldName.equals("passwordConfirmSignIn")) {
                        confirmPassword = item.getString("UTF-8");
                    } else {
                        throw new RuntimeException(String.format("unknown parameter received: %s", fieldName));
                    }
                } else { // is file
                    String path = Initializer.tempDir.getAbsolutePath();
                    File userFilesDir = new File(path);
                    if (!userFilesDir.exists() && !userFilesDir.mkdirs()) {
                        LOG.warn(String.format("unable to create file: %s", path));
                    }

                    // setting original file name as image name and generating unique file name
                    String fileName = path + "/image_" + System.currentTimeMillis() +
                            "." + ImageHelper.getFileExtension(new File(item.getName()));

                    imageFile = new File(fileName);
                    if (imageFile.exists() && imageFile.isFile()) {
                        String newFileName = FileHelper.generateFileName(imageFile);
                        imageFile = new File(newFileName);
                    }

                    // validating field name
                    String fieldName = item.getFieldName();
                    if (!fieldName.equals("userAvatar")) {
                        //TODO: poslat with unknown field
                    }

                    // writing file to disk
                    if (imageFile.getName().length() > 0 && !imageFile.exists()) {
                        item.write(imageFile);
                    }
                }
            }
        } catch (FileUploadException ex) {
            String errMsg = "Error occurred while uploading file: " + ex.getMessage();
            LOG.error(errMsg, ex);
            session.setAttribute("errMessage", errMsg);
        } catch (Exception e) {
            String errMsg = "Exception was thrown while adding a new post: " + e.getMessage();
            LOG.error(errMsg, e);
            session.setAttribute("errMessage", errMsg);
        }

        // validating passwords
        if (!password.equals(confirmPassword)) {
            String passErr = "Password does not match";
            model.addAttribute("passwordNotMatchErr", passErr);

            return "secure/sign-up";
        }

        try {
            // initializing result object
            Manager manager = new Manager();
            manager.setLogin(username);
            manager.setPassword(password);

            // set avatar
            int scaledWidth = 40;
            int scaledHeight = 40;
            Util.fileResizeAndWriteToSpecificFolder(imageFile.getAbsolutePath(), scaledWidth, scaledHeight);

            // assign avatar
            //TODO: assign avatar file (bytes) to creating object

            // save manager instance to DB
            managerService.addManager(manager);

            // get added manager and set in session
            manager = managerService.getManagerByLogin(username);
            session.setAttribute("manager", manager);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            //TODO: handle error
        }

        return "redirect:/";
    }

    // endregion

    // region <LOG-OUT CASES>

    @RequestMapping("/logout-page")
    public String toLogOutPage(Model model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    // endregion

}