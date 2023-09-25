import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class index {

    public static void main(String[] args) {
        boolean continueProgram = true;

        while (continueProgram) {
            JOptionPane.showMessageDialog(null, "Bienvenido a mi Challenge ONE Back End: Crea tu propio conversor de moneda.", "Challenge", JOptionPane.INFORMATION_MESSAGE, null);

            Object[] tipoConversor = {
                "Conversor de divisa",
                "Conversor de temperatura"
            };

            int conversorElegido = JOptionPane.showOptionDialog(null, "Elija el conversor:",
                "Selección de conversor", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, tipoConversor, tipoConversor[0]);

            if (conversorElegido == 0) {
                // Define currency options
                String[] divisas = {
                    "Peso Mexicano",
                    "Dolar",
                    "Euro",
                    "Yen Japonés",
                    "Libras Esterlinas",
                    "Won sul-coreano"
                };

                // Select the first currency
                JComboBox<String> primerDivisaComboBox = new JComboBox<>(divisas);
                JOptionPane.showOptionDialog(
                    null,
                    primerDivisaComboBox,
                    "Seleccione la divisa de la cantidad que va a convertir",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    null);

                // Input amount to convert
                String inputCantidad = null;
                boolean validInput = false;

                while (!validInput) {
                    inputCantidad = JOptionPane.showInputDialog(null, "Escriba la cantidad a convertir", "Cantidad", JOptionPane.OK_CANCEL_OPTION);

                    if (inputCantidad == null) {
                        // User clicked cancel, exit the program
                        System.exit(0);
                    }

                    if (isNumeric(inputCantidad)) {
                        validInput = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad ingresada no es válida. Por favor, ingrese un valor numérico.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                try {
                    // Select the second currency
                    JComboBox<String> segundaDivisaComboBox = new JComboBox<>(divisas);
                    JOptionPane.showOptionDialog(
                        null,
                        segundaDivisaComboBox,
                        "Seleccione la divisa a la que desea convertir",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        null,
                        null);

                    // Convert currencies
                    double cantidadAconvertir = Double.parseDouble(inputCantidad);
                    double conversionRate = getcambio(primerDivisaComboBox.getSelectedIndex(), segundaDivisaComboBox.getSelectedIndex());
                    double cantidadConvertida = cantidadAconvertir * conversionRate;

                    // Display result
                    String resultMessage = String.format("%.2f %s es igual a %.2f %s", cantidadAconvertir, divisas[primerDivisaComboBox.getSelectedIndex()], cantidadConvertida, divisas[segundaDivisaComboBox.getSelectedIndex()]);

                    JOptionPane.showMessageDialog(null, resultMessage, "Conversión", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "La cantidad ingresada no es válida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (conversorElegido == 1) {
                // Temperature converter
                String[] countries = {
                    "México CDMX",
                    "España",
                    "Corea del Sur",
                    "Reino Unido",
                    "Estados Unidos",
                    "Francia"
                };

                // Select origin country
                JComboBox<String> originCountryComboBox = new JComboBox<>(countries);
                JOptionPane.showOptionDialog(
                    null,
                    originCountryComboBox,
                    "Seleccione el país de origen",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    null);

                // Select destination country
                JComboBox<String> destinationCountryComboBox = new JComboBox<>(countries);
                JOptionPane.showOptionDialog(
                    null,
                    destinationCountryComboBox,
                    "Seleccione el país de destino",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    null);

                // Get temperatures for selected countries
                double originTemperature = getTemperature(originCountryComboBox.getSelectedIndex());
                double destinationTemperature = getTemperature(destinationCountryComboBox.getSelectedIndex());

                // Display temperatures
                String resultMessage = String.format("La temperatura en %s es %.2f°C y en %s es %.2f°C", countries[originCountryComboBox.getSelectedIndex()], originTemperature, countries[destinationCountryComboBox.getSelectedIndex()], destinationTemperature);

                JOptionPane.showMessageDialog(null, resultMessage, "Conversión de Temperatura", JOptionPane.INFORMATION_MESSAGE);
            }

            int option = JOptionPane.showConfirmDialog(null, "¿Desea continuar utilizando el programa?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Programa Finalizado", "Adiós", JOptionPane.INFORMATION_MESSAGE);
                continueProgram = false;
            }
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

   
        public static double getcambio(int fromCurrencyIndex, int toCurrencyIndex) {
            double[][] cambio = {
                {1.0, 0.059, 0.055, 8.65, 0.047, 77.84},
                {17.08, 1.0, 0.94, 151.73, 0.81, 1329.41},
                {18.23, 1.07, 1.0, 157.73, 0.86, 1418.95},
                {0.12, 0.0068, 0.0055, 1.0 / 18303.0, 18303.0, 9.0},
                {21.15, 1.0 / 24.0, 1646.0, 1646.0, 1.0},
                {77.0 / 21.0, 1329.0 / 77.0, 1418.0 / 77.0, 9.0 / 77.0, 1.0}
            };


        return cambio[fromCurrencyIndex][toCurrencyIndex];
    }

        public static double getTemperature(int countryIndex) {
            double[] temperatures = {24.0, 24.0, 23.0, 13.0, 20.0, 31.0};
            return temperatures[countryIndex];
        }}