module projet1Annuaire {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.graphics;
	requires javafx.base;
	exports fr.isika.cda18.projet1Annuaire.files;
	
	opens application to javafx.graphics, javafx.fxml,javafx.base;
	opens fr.isika.cda18.projet1Annuaire.model to javafx.graphics, javafx.fxml,javafx.base;
}
