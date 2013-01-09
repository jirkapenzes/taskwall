package resources;

/**
 * Author: Jirka Pénzeš
 * Date: 5.12.12
 * Time: 9:55
 */
public class TextResources {
    public static final String APPLICATION_NAME = "Task Wall";
    public static final String NULL_TEXT = "NULL";
    public static final String INIT_BOARD = "My first board";
    public static final String AUTO_SAVE = "Uloženo";

    public static class Dialogs {
        public static final String CREATE_BOARD_TITLE = "Create new board";
        public static final String CREATE_BOARD_MESSAGE = "Enter a board name: ";
        public static final String RENAME_BOARD_TITLE = "Create new board";
        public static final String RENAME_BOARD_MESSAGE = "Enter a board name: ";
        public static final String CREATE_STICKER_TITILE = "Přidání lístečku";
        public static final String CREATE_STICKER_MESSAGE = "Zadejte název nového lístečku";
        public static final String NOT_AUTHORIZET_MESSAGE = "Uživatel nebyl autorizován";
        public static final String NOT_AUTHORIZET_TITLE = "Autorizace";
        public static final String AUTHORIZET_MESSAGE = "Zadej heslo";
        public static final String AUTHORIZET_TITLE = "Autorizace";
        public static final String CONFIRM_SAVE_MESSAGE = "Uložení proběhlo v pořádku";
        public static final String CONFIRM_SAVE_TITLE = "Uložení";
        public static final String REMOVE_BOARD_ERR1_TITLE = "Chyba";
        public static final String REMOVE_BOARD_ERR1_MESSAGE = "Nástěnku nelze odstranit (jedna musí zůstat)";
        public static final String REMOVE_STICKERS_MESSAGE = "Zvolte lísteček, který chcete odstranit";
        public static final String REMOVE_STICKERS_TITLE = "Odstranění lístečku";
        public static final String REMOVE_STICKER_ERR1_TITLE = "Chyba";
        public static final String REMOVE_STICKER_ERR1_MESSAGE = "Není co mazat";
        public static final String CHANGE_OLD_PASSWORD = "Zadej staré heslo";
        public static final String CHANGE_NEW_PASSWORD = "Zadej nové heslo" ;
        public static final String CHANGE_PASSWORD_COMPLETE_MESSAGE = "Nové heslo bylo úspěšhně nastaveno";
        public static final String CHANGE_PASSWORD_COMPLETE_TITLE = "Změna hesla";
        public static final String NOT_CHANGE_PASSWORD_ERROR_MESSAGE = "Změna hesla neproběhla v pořádku";
        public static final String NOT_CHANGE_PASSWORD_ERROR_TITLE = "Změna heslo";
        public static final String INVALID_INPUT_MESSAGE ="Špatně zadaný vstup" ;
        public static final String INVALID_INPUT_TITLE = "Error";
    }

    public static class Menu {
        public static final String CREATE_BOARD = "Nová nastěnka";
        public static final String RENAME_BOARD = "Přejmenovat nástěnku";
        public static final String SAVE_STICKERS = "Uložit lístečky";
        public static final String ADD_STICKER = "Přidat lísteček";
        public static final String SHOW_NAVIGATION = "Zobrazit navigaci";
        public static final String REMOVE_BOARD = "Odstranit nástěnku";
        public static final String REMOVE_STICKERS = "Ostranit lístečky";
        public static final String CHANGE_PASSWORD = "Změnit heslo";
    }
}
