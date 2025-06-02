import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AbidosCalculatorGUI extends JFrame {
    private static final int AZUL_REQ = 33;
    private static final int VERDE_REQ = 45;
    private static final int GRIS_REQ = 86;
    private static final int ORO_POR_LOTE = 344;
    private static final int ENERGIA_POR_LOTE = 257;
    private static final int TIEMPO_MINUTOS_POR_LOTE = 46;

    private JTextField grisInput, verdeInput, azulInput, energiaInput;
    private JTextArea resultArea;

    public AbidosCalculatorGUI() {
        setTitle("Abidos Crafting Calculator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        grisInput = new JTextField();
        verdeInput = new JTextField();
        azulInput = new JTextField();
        energiaInput = new JTextField();

        inputPanel.add(new JLabel("Madera Gris:"));
        inputPanel.add(grisInput);
        inputPanel.add(new JLabel("Madera Verde:"));
        inputPanel.add(verdeInput);
        inputPanel.add(new JLabel("Madera Azul:"));
        inputPanel.add(azulInput);
        inputPanel.add(new JLabel("Energía Disponible:"));
        inputPanel.add(energiaInput);

        JButton calcularButton = new JButton("Calcular");
        calcularButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularResultado();
            }
        });

        inputPanel.add(new JLabel(""));
        inputPanel.add(calcularButton);

        resultArea = new JTextArea();
		resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void calcularResultado() {
/*
        int gris = parseInt(grisInput.getText());
        int verde = parseInt(verdeInput.getText());
        int azul = parseInt(azulInput.getText());
        int energia = parseInt(energiaInput.getText());
*/
		int gris = parseInt(grisInput.getText());
		int verde = parseInt(verdeInput.getText());
		int azul = parseInt(azulInput.getText());
		int energia = parseInt(energiaInput.getText());

		if (gris < 0 || verde < 0 || azul < 0 || energia < 0) {
			JOptionPane.showMessageDialog(this, "Por favor, ingresa solo números válidos en todos los campos.");
			return;
		}
		
        StringBuilder sb = new StringBuilder();
        sb.append("Inventario inicial:\n");
        sb.append("Gris: ").append(gris).append(", Verde: ").append(verde).append(", Azul: ").append(azul).append("\n\n");

        int lotesBase = Math.min(Math.min(gris / GRIS_REQ, verde / VERDE_REQ), azul / AZUL_REQ);
        int grisUsado = lotesBase * GRIS_REQ;
        int verdeUsado = lotesBase * VERDE_REQ;
        int azulUsado = lotesBase * AZUL_REQ;

        gris -= grisUsado;
        verde -= verdeUsado;
        azul -= azulUsado;

        sb.append("Lotes posibles sin transformar: ").append(lotesBase).append("\n\n");

        int verdesPolvos = 0, grisesPolvos = 0;
        int polvosTotal = 0;
        int verdesCompras = 0, grisesCompras = 0;
        int azulesComprados = 0;

        while (true) {
            int lotesExtras = Math.min(Math.min(gris / GRIS_REQ, verde / VERDE_REQ), (azul + azulesComprados) / AZUL_REQ);
            int lotesMaxEnergia = energia / ENERGIA_POR_LOTE;
            if (lotesBase + lotesExtras >= lotesMaxEnergia) break;

            if ((azul + azulesComprados) < (lotesBase + lotesExtras + 1) * AZUL_REQ) {
                if (verde >= 50) {
                    verde -= 50;
                    verdesPolvos += 50;
                    polvosTotal += 80;
                    verdesCompras++;
                } else if (gris >= 100) {
                    gris -= 100;
                    grisesPolvos += 100;
                    polvosTotal += 80;
                    grisesCompras++;
                } else break;

                if (polvosTotal >= 10) {
                    int compras = polvosTotal / 10;
                    azulesComprados += compras * 10;
                    polvosTotal -= compras * 10;
                }
            } else break;
        }

        azul += azulesComprados;

        //int lotesTotales = Math.min(Math.min(gris / GRIS_REQ, verde / VERDE_REQ), azul / AZUL_REQ);
		int lotesExtras = Math.min(Math.min(gris / GRIS_REQ, verde / VERDE_REQ), azul / AZUL_REQ);
		int lotesTotales = lotesBase + Math.min(lotesExtras, (energia - lotesBase * ENERGIA_POR_LOTE) / ENERGIA_POR_LOTE);
        lotesTotales = Math.min(lotesTotales, energia / ENERGIA_POR_LOTE);
        int oroTotal = lotesTotales * ORO_POR_LOTE;
        int energiaUsada = lotesTotales * ENERGIA_POR_LOTE;

/*
        sb.append("--- CONVERSIÓN A POLVOS ---\n");
        sb.append("Verdes transformados: ").append(verdesPolvos)
          .append(" → ").append(verdesCompras * 80).append(" polvitos\n")
          .append(" → ").append(verdesCompras).append(" compras de 50 maderas verdes\n")
          .append(" → Verde restante: ").append(verde).append("\n\n");
        sb.append("Grises transformados: ").append(grisesPolvos)
          .append(" → ").append(grisesCompras * 80).append(" polvitos\n")
          .append(" → ").append(grisesCompras).append(" compras de 100 maderas grises\n")
          .append(" → Gris restante: ").append(gris).append("\n\n");
*/

		sb.append("--- CONVERSIÓN A POLVOS ---\n");

		int verdeReservada = lotesBase * VERDE_REQ;
		int grisReservada = lotesBase * GRIS_REQ;

		sb.append("✔ Verde reservada para ").append(lotesBase).append(" lotes base: ").append(verdeReservada).append("\n");
		sb.append("Verdes transformados: ").append(verdesPolvos)
		  .append(" → ").append(verdesCompras * 80).append(" polvitos\n")
		  .append(" → ").append(verdesCompras).append(" compras de 50 maderas verdes\n")
		  .append(" → Verde restante (excedente sin transformar): ").append(verde).append("\n\n");

		sb.append("✔ Gris reservada para ").append(lotesBase).append(" lotes base: ").append(grisReservada).append("\n");
		sb.append("Grises transformados: ").append(grisesPolvos)
		  .append(" → ").append(grisesCompras * 80).append(" polvitos\n")
		  .append(" → ").append(grisesCompras).append(" compras de 100 maderas grises\n")
		  .append(" → Gris restante (excedente sin transformar): ").append(gris).append("\n\n");
	  
		  
        sb.append("Total polvitos obtenidos: ").append(verdesCompras * 80 + grisesCompras * 80).append("\n");
        sb.append("Total polvitos utilizados: ").append(azulesComprados).append("\n");
        sb.append("Azules comprados: ").append(azulesComprados).append(" (en ").append(azulesComprados / 10).append(" compras de 10)\n\n");
		
		int inventarioFinalGris = gris + grisUsado;
		int inventarioFinalVerde = verde + verdeUsado;
		int inventarioFinalAzul = azul + azulUsado;


        //sb.append("Inventario después de convertir polvitos y comprar:\n");
        //sb.append("Azul: ").append(azul).append("\nVerde: ").append(verde).append("\nGris: ").append(gris).append("\n\n");
		sb.append("Inventario después de convertir polvitos y comprar:\n");
		sb.append("Azul: ").append(inventarioFinalAzul).append("\n");
		sb.append("Verde: ").append(inventarioFinalVerde).append("\n");
		sb.append("Gris: ").append(inventarioFinalGris).append("\n\n");

        sb.append("PODÉS FABRICAR: ").append(lotesTotales).append(" lotes de 10 Abidos (total: ").append(lotesTotales * 10).append(")\n");
        sb.append("ORO requerido: ").append(oroTotal).append("\n");
        sb.append("ENERGÍA requerida: ").append(energiaUsada).append("\n\n");

        int[] ventanas = new int[4];
        for (int i = 0; i < lotesTotales && i < 40; i++) {
            ventanas[i % 4]++;
        }

        int maxTiempo = 0;
        sb.append("TIEMPO total de crafteo (máximo por ventana):\n");
        for (int i = 0; i < 4; i++) {
            int minutos = ventanas[i] * TIEMPO_MINUTOS_POR_LOTE;
            if (minutos > maxTiempo) maxTiempo = minutos;
        }
        sb.append(maxTiempo / 60).append(" horas y ").append(maxTiempo % 60).append(" minutos (").append(maxTiempo).append(" minutos en total)\n\n");

        sb.append("Crafteos por ventana:\n");
        for (int i = 0; i < 4; i++) {
            int minutos = ventanas[i] * TIEMPO_MINUTOS_POR_LOTE;
            sb.append("Ventana ").append(i + 1).append(": ").append(ventanas[i]).append(" crafteos (")
              .append(minutos / 60).append(" horas y ").append(minutos % 60).append(" minutos)\n");
        }

        if (lotesTotales > 40) {
            sb.append("\n⚠️ Tenés más lotes disponibles, pero las 4 ventanas están llenas. Podrías hacer ")
              .append(lotesTotales - 40).append(" lotes adicionales.\n");
        }

        resultArea.setText(sb.toString());
    }

	private int parseInt(String text) {
		try {
			return Integer.parseInt(text.trim());
		} catch (NumberFormatException e) {
			return -1;
		}
	}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AbidosCalculatorGUI().setVisible(true));
    }
}