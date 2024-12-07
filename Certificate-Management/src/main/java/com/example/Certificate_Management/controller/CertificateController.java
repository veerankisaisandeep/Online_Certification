package com.example.Certificate_Management.controller;

import com.example.Certificate_Management.entity.Certificate;
import com.example.Certificate_Management.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    // LOGIN PAGE (GET and POST)
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Returns login.html
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        // Implement authentication logic (validate username and password)
        return "redirect:/home"; // Redirect to home page on successful login
    }

    // LOGOUT PAGE
    @GetMapping("/logout")
    public String logout() {
        // Clear session or token (logout logic)
        return "redirect:/login"; // Redirect back to login page
    }

    // HOME PAGE
    @GetMapping("/home")
    public String homePage(Model model) {
        List<Certificate> certificates = certificateService.getAllCertificates();
        model.addAttribute("certificates", certificates);
        return "home"; // Returns home.html
    }

    // CERTIFICATES PAGE (LIST ALL)
    @GetMapping("/certificates")
    public String listCertificates(Model model) {
        List<Certificate> certificates = certificateService.getAllCertificates();
        model.addAttribute("certificates", certificates);
        return "certificates"; // returns certificates.html
    }

    // CREATE CERTIFICATE FORM
    @GetMapping("/certificates/new")
    public String createCertificateForm(Model model) {
        model.addAttribute("certificate", new Certificate());
        return "create_certificate"; // returns create_certificate.html
    }

    // SAVE NEW CERTIFICATE (WITH FILE UPLOAD)
    @PostMapping("/certificates")
    public String saveCertificate(@ModelAttribute("certificate") Certificate certificate, 
                                  @RequestParam("file") MultipartFile file, Model model) throws IOException {

        // Handle the file upload
        if (!file.isEmpty()) {
            // Get the original file name
            String fileName = file.getOriginalFilename();

            // Define the directory where you want to save the file
            String uploadDir = "uploads/";

            // Create directory if it doesn't exist
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Define the path to save the file
            Path path = Paths.get(uploadDir + fileName);

            // Save the file
            file.transferTo(path);

            // Set the file URL or path in the certificate
            certificate.setFileUrl(uploadDir + fileName);
        }

        // Save the certificate to the database
        certificateService.saveCertificate(certificate);

        // Fetch the updated list of certificates after saving the new one
        List<Certificate> certificates = certificateService.getAllCertificates();
        
        // Add the list of certificates to the model to be displayed on the certificates page
        model.addAttribute("certificates", certificates);

        return "certificates"; // Return to the certificates page with updated list
    }

    // EDIT CERTIFICATE FORM (ID in URL)
    @GetMapping("/certificates/edit/{id}")
    public String editCertificateForm(@PathVariable Long id, Model model) {
        Certificate certificate = certificateService.getCertificateById(id); // Fetch certificate by ID
        if (certificate != null) {
            model.addAttribute("certificate", certificate); // Add the certificate to the model for editing
            return "edit_certificate"; // returns edit_certificate.html
        } else {
            return "redirect:/certificates"; // Redirect to the certificates list if certificate not found
        }
    }

    // UPDATE CERTIFICATE
    @PostMapping("/certificates/edit/{id}")
    public String updateCertificate(@PathVariable Long id,
                                    @ModelAttribute("certificate") Certificate certificate,
                                    @RequestParam("file") MultipartFile file) throws IOException {

        // If a new file is uploaded, handle the file upload
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String uploadDir = "uploads/";

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            Path path = Paths.get(uploadDir + fileName);
            file.transferTo(path);
            certificate.setFileUrl(uploadDir + fileName); // Save file URL
        }

        // Update the certificate in the database using the provided ID
        certificate.setId(id); // Ensure the certificate has the correct ID before updating
        certificateService.updateCertificate(id, certificate);

        return "redirect:/certificates"; // Redirect to certificates page after updating
    }

    // DELETE CERTIFICATE
    @GetMapping("/certificates/delete/{id}")
    public String deleteCertificate(@PathVariable Long id) {
        certificateService.deleteCertificate(id);
        return "redirect:/certificates"; // Redirect to certificates page after deletion
    }

    // ABOUT PAGE
    @GetMapping("/about")
    public String aboutPage() {
        return "about"; // returns about.html
    }

    // CONTACT PAGE (GET & POST for feedback)
    @GetMapping("/contact")
    public String contactPage() {
        return "contact"; // returns contact.html
    }

    @PostMapping("/contact/submit-feedback")
    public String submitFeedback() {
        // Process feedback form (save feedback, send email, etc.)
        return "redirect:/home"; // Redirect to home page after feedback submission
    }

    // ADMIN DASHBOARD
    @GetMapping("/admin")
    public String adminDashboard() {
        return "admin_dashboard"; // returns admin_dashboard.html
    }
}
