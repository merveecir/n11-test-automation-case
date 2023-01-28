package org.example.operation;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class ExcelOperation {

    private Connection connection;
    private Recordset recordset;
    private Logger log = Logger.getLogger(ExcelOperation.class);
    private String errorMessage = "";

    /**
     * Excel dosyası üzerinde sql komutları çalıştırabilmek için bağlantı açar.
     *
     * @param excelPath Excel dosyasının tam dosya yolu ve ismini girin. Örneğin: C:/deneme.xlsx
     */
    public void openExcelConnection(String excelPath) {

        try {
            Fillo fillo = new Fillo();
            connection = fillo.getConnection(excelPath);
        } catch (FilloException filloException) {
            errorMessage = String.format("Excel dosyasına bağlanırken sorun oluştu! Hata mesajı: %s", filloException.getMessage());
            log.error(errorMessage);
            Assert.fail(errorMessage);
        }
    }

    /**
     * Bu metodu kullanılmadan önce, connection'un açık durumda olduğundan emin olun!
     *
     * @param sqlCommand execute edilecek sql komutu.
     */
    public Recordset executeQuery(String sqlCommand) {

        try {
            recordset = connection.executeQuery(sqlCommand);
            String logMessage = String.format("'%s' komutu çalıştırıldı.", sqlCommand);
            log.info(logMessage);
        } catch (Exception ex) {
            errorMessage = String.format("'%s' komutu çalıştırılırken sorun oluştu! Hata mesajı: %s", sqlCommand, ex.getMessage());
            log.info(errorMessage);
        }
        return recordset;
    }

    /**
     * @param sqlCommand
     * @return
     */
    public int executeUpdate(String sqlCommand) {

        int result = -1;
        try {
            result = connection.executeUpdate(sqlCommand);
            String logMessage = String.format("'%s' komutu çalıştırıldı.", sqlCommand);
            log.info(logMessage);
        } catch (Exception ex) {
            String errorMessage = String.format("'%s' komutu çalıştırılırken sorun oluştu! Hata mesajı: %s", sqlCommand, ex.getMessage());
            log.error(errorMessage);
            log.error(ex.getStackTrace());
        }
        return result;
    }

    public void closeExcelConnection() {

        try {
            if (recordset != null) {
                recordset.close();
                connection.close();
            }
        } catch (Exception ex) {
            errorMessage = String.format("Excel dosyası bağlantısı kapatılırken sorun oluştu! Hata mesajı: %s", ex.getMessage());
            log.error(errorMessage);
            Assert.fail(errorMessage);
        }
    }
}
