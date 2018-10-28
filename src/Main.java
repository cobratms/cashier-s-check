import java.io.*;
import java.util.Locale;

/**
 * @author Nail Zinnurov
 * cobratms@gmail.com
 * on 06.10.2018
 */

public class Main {
    public static void main(String[] args)  throws IOException {

        String[] productsAndPrice = getProductName().split("\n");
        double[] productTotalPrice = getPrice(productsAndPrice);
        double totalPrice = getTotalPrice(productTotalPrice);
        String cashVoucher = formatText(productsAndPrice, productTotalPrice, totalPrice);
        printCashVoucher(cashVoucher);


    }

    private static String formatText(String[] productsAndPrice, double[] productTotalPrice, double totalPrice) {
        StringBuilder bilder = new StringBuilder();

        bilder.append(String.format("%s%12s%9s%13s%n", "Наименование", "Цена", "Кол-во", "Стоимость"));
        bilder.append("===============================================" + "\n");
        bilder.append(String.format(Locale.ENGLISH,"%s%17s%2s%7s%9s%.2f%n", productsAndPrice[0], productsAndPrice[2], "x", productsAndPrice[1], "=", productTotalPrice[0]));
        bilder.append(String.format(Locale.ENGLISH,"%s%9s%2s%7s%9s%.2f%n", productsAndPrice[3], productsAndPrice[5], "x", productsAndPrice[4], "=", productTotalPrice[1]));
        bilder.append(String.format(Locale.ENGLISH,"%s%16s%2s%7s%8s%.2f%n", productsAndPrice[6], productsAndPrice[8], "x", productsAndPrice[7], "=", productTotalPrice[2]));
        bilder.append(String.format(Locale.ENGLISH,"%s%9s%2s%7s%8s%.2f%n", productsAndPrice[9], productsAndPrice[11], "x", productsAndPrice[10], "=", productTotalPrice[3]));
        bilder.append(String.format(Locale.ENGLISH,"%s%12s%2s%7s%9s%.2f%n", productsAndPrice[12], productsAndPrice[14], "x", productsAndPrice[13], "=", productTotalPrice[4]));
        bilder.append(String.format(Locale.ENGLISH,"%s%9s%2s%7s%8s%.2f%n", productsAndPrice[15], productsAndPrice[17], "x", productsAndPrice[16], "=", productTotalPrice[5]));
        bilder.append(String.format(Locale.ENGLISH,"%s%9s%2s%7s%8s%.2f%n", productsAndPrice[18], productsAndPrice[20], "x", productsAndPrice[19], "=", productTotalPrice[6]));
        bilder.append(String.format(Locale.ENGLISH,"%s%12s%2s%7s%7s%.2f%n", productsAndPrice[21], productsAndPrice[23], "x", productsAndPrice[22], "=", productTotalPrice[7]));
        bilder.append("===============================================" + "\n");
        bilder.append(String.format(Locale.ENGLISH,"%s%41.2f", "Итого:", totalPrice));

        String cashVoucher = bilder.toString();
        return  cashVoucher;
    }

    private static void printCashVoucher(String cashVoucher) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("cashVoucher.txt"))) {
                writer.write(cashVoucher);
                writer.flush();
        }catch (IOException e) {
            throw new IOException("Output ERROR");
        }
    }

    private static double getTotalPrice(double[] productTotalPrice) {
        double totalPrice = 0.0;
        for(int i = 0; i < productTotalPrice.length; i++) {
            totalPrice = totalPrice + productTotalPrice[i];
        }
        return  totalPrice;

    }

    private static double[] getPrice(String[] productsAndPrice) {
        double[] total = new double[productsAndPrice.length / 3];

        int j = 0;
        for(int i = 0; i < productsAndPrice.length - 2; i+=3) {
            total[j] = Double.parseDouble(productsAndPrice[i + 1]) * Double.parseDouble(productsAndPrice[i + 2]);
            j++;
        }

        return total;
    }

    private static String getProductName() throws IOException {
        StringBuilder builder = new StringBuilder();
        try (FileReader file = new FileReader("products.txt")) {
            int symbol;

            while ((symbol = file.read()) != -1) {
                builder.append((char) symbol);
            }

        } catch (IOException e) {
            throw new IOException("Error in Read file");

        }
        String products = builder.toString();
        return products;
    }
}
