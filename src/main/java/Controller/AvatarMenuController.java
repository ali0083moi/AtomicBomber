package Controller;

import Model.App;
import Model.User;
import View.AvatarMenu;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class AvatarMenuController {
    private User loggedInUser = App.getLoggedInUser();

    public void randomAvatar() {
    }

    public void selectAvatar() {
    }

    public void selectAvatar(String avatar) {
    }

    public void changeAvatar() {
    }

    public void back() {
    }

    public void chooseAvatar(ImageView imageView, ImageView avatarImageView) {
        String avatarName = imageView.idProperty().getValue();
        String avatarPath = "/images/avatars/" + avatarName + ".png";
        AvatarMenu.setAvatarPath(avatarPath);
        Image avatar = new Image(Objects.requireNonNull(getClass().getResourceAsStream(avatarPath)));
        avatarImageView.setImage(avatar);
    }

    public void setNewAvatar() {
        if (AvatarMenu.getAvatarPath() == null || AvatarMenu.getAvatarPath().equals(loggedInUser.getAvatarPath())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Avatar already selected");
            alert.setHeaderText("You have already selected this avatar.");
            alert.setContentText("Please select a different avatar.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Change Avatar");
            alert.setHeaderText("Are you sure you want to change your avatar?");
            alert.setContentText("Your avatar will be changed to the selected one.");
            alert.showAndWait();
            if (alert.getResult().getText().equals("OK")) {
                loggedInUser.setAvatarPath(AvatarMenu.getAvatarPath());
            } else {
                alert.close();
            }
        }
    }

    public void setAvatarFromFile(Button openAvatarFromFile, ImageView avatarImageView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        fileChooser.setTitle("Select an image");
        fileChooser.setInitialDirectory(new java.io.File("src/main/resources/images/avatars"));
        File selectedFile = fileChooser.showOpenDialog(openAvatarFromFile.getScene().getWindow());
        if (selectedFile != null) {
            try {
                // Copy the selected file to the images directory
                File imagesDirectory = new File("src/main/resources/images/avatars");
                if (!imagesDirectory.exists()) {
                    imagesDirectory.mkdir();
                }

                String fileName = selectedFile.getName();
                File destinationFile = new File(imagesDirectory, fileName);
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Load the copied image into the ImageView
                Image image = new Image(destinationFile.toURI().toString());
                avatarImageView.setImage(image);
                AvatarMenu.setAvatarPath("/images/avatars/" + fileName);
                loggedInUser.setAvatarPath(AvatarMenu.getAvatarPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleDroppedImage(File file, ImageView avatarImageView) {
        try {
            // Load the dropped image into the ImageView
            File imagesDirectory = new File("src/main/resources/images/avatars");
            if (!imagesDirectory.exists()) {
                imagesDirectory.mkdir();
            }

            String fileName = file.getName();
            File destinationFile = new File(imagesDirectory, fileName);
            Files.copy(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Load the copied image into the ImageView
            Image image = new Image(destinationFile.toURI().toString());
            avatarImageView.setImage(image);
            AvatarMenu.setAvatarPath("/images/avatars/" + fileName);
            loggedInUser.setAvatarPath(AvatarMenu.getAvatarPath());
            // You can also perform additional actions here, such as saving the image to a specific location
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
}
