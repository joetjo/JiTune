package joprod.jitune.resources;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class JTStrings {

	public static final SimpleDateFormat DATE_DAY_FORMAT = new SimpleDateFormat("dd");
	public static final DecimalFormat AMOUNT_FORMAT = new DecimalFormat("0.00");
	
	public static final String APPNAME = "JiTune New Generation";

	public static final String DEVISE = "�";

	// MENU
	public static String menu_file = "JiTune";
	public static String menu_exit = "Quitter";
	public static String menu_select_account = "Selection du compte";

	// ERRORS
	public static String storage_error_title = "Erreur d'acc�s aux donn�es";
	public static String storage_error_io = "erreur d'acc�s au disque";
	public static String storage_error_serial = "erreur de lecture d'un bloc de donn�es";
	public static String storage_error_save = "erreur d'enregistrement des donn�es";
	
	// DATE
	public static String title_date_selection = "Periode active";
	public static String date_year = "Ann�e active : ";
	public static String date_from = " De  ";
	public static String date_to = "  �  ";

	public static String menu_view = "Affichage";
	public static String menu_comptes = "Liste des comptes";
	public static String menu_date_selection = "Selection de la p�riode active";

	// LOGIN
	public static String loginMessage = "V�rification d'identit�: Scanning R�tinien";

	// COMPTES
	public static String title_list_account = "Comptes";
	public static String title_select_account = "JiTune - S�lection de compte";
	public static String selection_active_account = "S�lection du compte actif";
	public static String selection_active_account_message = "La version actuelle de JiTune New Generation travaille sur un seul compte actif.";

	public static String[] mois = {
		"Janvier",
		"F�vrier",
		"Mars",
		"Avril",
		"Mai",
		"Juin",
		"Juillet",
		"Aout",
		"Septembre",
		"Octobre",
		"Novembre",
		"Decembre"
	};

	public static String table_title_category = "Cat�gorie";
	public static String table_title_total    = "Total";

}
