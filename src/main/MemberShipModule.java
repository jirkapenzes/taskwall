package main;

import app.common.MemberShip;
import resources.TextResources;

import javax.swing.*;

/**
 * Author: Jirka Pénzeš
 * Date: 17.12.12
 * Time: 12:37
 */
public class MemberShipModule implements IModule {

    private MemberShip memberShip;
    private boolean activate;

    public MemberShipModule(MemberShip memberShip) {
        this.memberShip = memberShip;
    }

    @Override
    public void activate() {
        JPasswordField passwordField = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, passwordField, TextResources.Dialogs.AUTHORIZET_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (okCxl == JOptionPane.OK_OPTION) {
            if (memberShip.authorize(new String(passwordField.getPassword()))) {
                return;
            }
        }
        JOptionPane.showMessageDialog(null, TextResources.Dialogs.NOT_AUTHORIZET_MESSAGE,
                TextResources.Dialogs.NOT_AUTHORIZET_TITLE, JOptionPane.ERROR_MESSAGE);

        System.exit(0);
    }

    public void changePassword() {
        JPasswordField oldPasswordField = new JPasswordField();
        int okOldPassword = JOptionPane.showConfirmDialog(null, oldPasswordField, TextResources.Dialogs.CHANGE_OLD_PASSWORD,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (okOldPassword == JOptionPane.OK_OPTION) {
            if (memberShip.authorize(new String(oldPasswordField.getPassword()))) {
                JPasswordField newPasswordField = new JPasswordField();
                int okNewPassword = JOptionPane.showConfirmDialog(null, newPasswordField, TextResources.Dialogs.CHANGE_NEW_PASSWORD,
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (okNewPassword == JOptionPane.OK_OPTION) {
                    String newPassword = new String(newPasswordField.getPassword());
                    if (validateInput(newPassword)) {
                        memberShip.setNewPassword(newPassword);
                        JOptionPane.showMessageDialog(null, TextResources.Dialogs.CHANGE_PASSWORD_COMPLETE_MESSAGE,
                                TextResources.Dialogs.CHANGE_PASSWORD_COMPLETE_TITLE, JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                }
            }
        }
        JOptionPane.showMessageDialog(null, TextResources.Dialogs.NOT_CHANGE_PASSWORD_ERROR_MESSAGE,
                TextResources.Dialogs.NOT_CHANGE_PASSWORD_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
    }

    private boolean validateInput(String input) {
        if (input == null)
            throw new RuntimeException();

        if (input.equals(""))
            throw new RuntimeException();

        return true;
    }
}
