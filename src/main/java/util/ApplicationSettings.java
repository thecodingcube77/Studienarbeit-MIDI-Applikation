package util;

public class ApplicationSettings {
    private static String selectedBoard = null; // FQBN soll hier rein
    private static String portAddress = null;
    private static String projectFolder = null;
    private static String projectName = null;

    private ApplicationSettings() {
    }

    public static void setSelectedBoard(String newBoard) {
        selectedBoard = newBoard;
    }
    public static String getSelectedBoard() {
        return selectedBoard;
    }
    public static boolean hasSelectedBoard() {
        return selectedBoard != null;
    }

    public static void setPortAddress(String newPortAddress) {
        portAddress = newPortAddress;
    }
    public static String getPortAddress() {
        return portAddress;
    }
    public static boolean hasPortAddress() {
        return portAddress != null;
    }

    public static String getProjectName() {
        return projectName;
    }

    public static void setProjectName(String newProjectName) {
        projectName = newProjectName;
    }

    public static String getProjectFolder() {
        return projectFolder;
    }

    public static void setProjectFolder(String newProjectFolder) {
        projectFolder = newProjectFolder;
    }
}
