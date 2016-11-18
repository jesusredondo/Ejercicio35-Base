import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.cert.PKIXRevocationChecker.Option;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaPrincipal {

	// La ventana principal, en este caso, guarda todos los componentes:
	JFrame ventana;
	JPanel panelImagen;
	JPanel panelEmpezar;
	JPanel panelPuntuacion;
	JPanel panelJuego;
	String textoEmpezarJuego = "�Pulse Go!";
	String textoJugando = "�Suerte!";
	String textoJuegofinalizado = "�Vuelva a Jugar, Pulse Go!";

	// Todos los botones se meten en un panel independiente.
	// Hacemos esto para que podamos cambiar después los componentes por otros
	JPanel[][] panelesJuego;
	JButton[][] botonesJuego;

	// Correspondencia de colores para las minas:
	Color correspondenciaColores[] = { Color.BLACK, Color.CYAN, Color.GREEN, Color.ORANGE, Color.RED, Color.RED,
			Color.RED, Color.RED, Color.RED, Color.RED };

	JButton botonEmpezar;
	JTextField pantallaPuntuacion;
	JTextField pantallaImagen;

	// LA VENTANA GUARDA UN CONTROL DE JUEGO:
	ControlJuego juego;

	// Constructor, marca el tamaño y el cierre del frame
	public VentanaPrincipal() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 700, 500);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setTitle("Buscaminas");
		juego = new ControlJuego();
	}

	// Inicializa todos los componentes del frame
	public void inicializarComponentes() {

		// Definimos el layout:
		ventana.setLayout(new GridBagLayout());

		// Inicializamos componentes
		panelImagen = new JPanel();
		panelImagen.setLayout(new GridLayout(1,1));
		panelEmpezar = new JPanel();
		panelEmpezar.setLayout(new GridLayout(1, 1));
		panelPuntuacion = new JPanel();
		panelPuntuacion.setLayout(new GridLayout(1, 1));
		panelJuego = new JPanel();
		panelJuego.setLayout(new GridLayout(10, 10));

		botonEmpezar = new JButton("Go!");
		pantallaPuntuacion = new JTextField("0");
		pantallaImagen = new JTextField(textoEmpezarJuego);
		pantallaImagen.setEditable(false);
		pantallaImagen.setHorizontalAlignment(SwingConstants.CENTER);
		pantallaImagen.setAlignmentY(SwingConstants.CENTER);
		pantallaPuntuacion.setEditable(false);
		pantallaPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);

		// Bordes y colores:
		panelImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelEmpezar.setBorder(BorderFactory.createTitledBorder("Empezar"));
		panelPuntuacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelJuego.setBorder(BorderFactory.createTitledBorder("Juego"));

		// Colocamos los componentes:
		// AZUL
		GridBagConstraints settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelImagen, settings);
		// VERDE
		settings = new GridBagConstraints();
		settings.gridx = 1;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelEmpezar, settings);
		// AMARILLO
		settings = new GridBagConstraints();
		settings.gridx = 2;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelPuntuacion, settings);
		// ROJO
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 1;
		settings.weightx = 1;
		settings.weighty = 10;
		settings.gridwidth = 3;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelJuego, settings);

		// Paneles
		panelesJuego = new JPanel[10][10];
		for (int i = 0; i < panelesJuego.length; i++) {
			for (int j = 0; j < panelesJuego[i].length; j++) {
				panelesJuego[i][j] = new JPanel();
				panelesJuego[i][j].setLayout(new GridLayout(1, 1));
				panelJuego.add(panelesJuego[i][j]);
			}
		}

		// Botones
		botonesJuego = new JButton[10][10];
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j] = new JButton("-");
				panelesJuego[i][j].add(botonesJuego[i][j]);
			}
		}

		// BotónEmpezar:
		panelEmpezar.add(botonEmpezar);
		panelPuntuacion.add(pantallaPuntuacion);
		
		//Pantalla Imagen
		panelImagen.add(pantallaImagen);

	}

	/**
	 * Método que inicializa todos los lísteners que necesita inicialmente el
	 * programa
	 * Incializo el Boton GO
	 */
	public void inicializarListeners() {
		
		botonEmpezar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//si el boton empezar est� desactivado se juega
				if (!botonEmpezar.isEnabled()) {
					pantallaImagen.setText(textoJugando);
					botonEmpezar.setEnabled(false);
					for (int i = 0; i < botonesJuego.length; i++) {
						for (int j = 0; j < botonesJuego[i].length; j++) {
							botonesJuego[i][j].addActionListener(new ActionBoton(VentanaPrincipal.this, i, j));
						}
					}
				}else{
					//si el boton empezar est� activado se reinicia el juego
					inicializarComponentes();
					juego =  new ControlJuego();
				}
				
			}
		});
	}

	/**
	 * Método que pinta en la pantalla el número de minas que hay alrededor de
	 * la celda 
	 * Saca el botón que haya en la celda determinada y añade un
	 * JLabel centrado y no editable con el número de minas alrededor. Se pinta
	 * el color del texto según la siguiente correspondecia (consultar la
	 * variable correspondeciaColor): - 0 : negro - 1 : cyan - 2 : verde - 3 :
	 * naranja - 4 ó más : rojo
	 * 
	 * @param i:
	 *            posición vertical de la celda.
	 * @param j:
	 *            posición horizontal de la celda.
	 */
	public void mostrarNumMinasAlrededor(int i, int j) {				
		JLabel label = new JLabel(String.valueOf(juego.getMinasAlrededor(i, j)));
		panelesJuego[i][j].removeAll();
		panelesJuego[i][j].add(label);
		label.setHorizontalAlignment(0);
		label.setEnabled(false);
		switch (Integer.parseInt(label.getText())) {
		case 0: {
			label.setBackground(correspondenciaColores[0]);
			label.setOpaque(true);
			break;
		}
		case 1: {
			label.setBackground(correspondenciaColores[1]);
			label.setOpaque(true);
			break;
		}
		case 2: {
			label.setBackground(correspondenciaColores[2]);
			label.setOpaque(true);
			break;
		}
		case 3: {
			label.setBackground(correspondenciaColores[3]);
			label.setOpaque(true);
			break;
		}
		default: {
			label.setBackground(correspondenciaColores[4]);
			label.setOpaque(true);
			break;
		}
		}
		refrescarPantalla();

	}

	/**
	 * Método que muestra una ventana que muestra el fin del juego
	 * 
	 * @param porExplosion
	 *            : Un booleano que indica si es final del juego porque ha
	 *            explotado una mina (true) o bien porque hemos desactivado
	 *            todas (false)
	 * @post : Todos los botones se desactivan excepto el de volver a iniciar el
	 *       juego.
	 */
	public void mostrarFinJuego(boolean porExplosion) {
		JLabel label; 
		if (porExplosion) {
			//si se ha perdido
			JOptionPane.showMessageDialog(ventana, "HA PERDIDO");
			pantallaImagen.setText(textoJuegofinalizado);
			botonEmpezar.setEnabled(true);
		}else{
			//si se ha ganado
			JOptionPane.showMessageDialog(ventana, "HA GANADO");
			pantallaImagen.setText(textoJuegofinalizado);
			botonEmpezar.setEnabled(true);
		}
		for (int i = 0; i < panelesJuego.length; i++) {
			for (int k = 0; k < panelesJuego[i].length; k++) {
				label = new JLabel(String.valueOf(juego.getMinasAlrededor(i, k)));
				panelesJuego[i][k].removeAll();
				panelesJuego[i][k].add(label);
				label.setHorizontalAlignment(0);
				label.setEnabled(false);
			}
		}
		
	}

	/**
	 * Método que muestra la puntuación por pantalla.
	 */
	public void actualizarPuntuacion() {
		pantallaPuntuacion.setText(String.valueOf(juego.getPuntuacion()));
	}

	/**
	 * Método para refrescar la pantalla
	 */
	public void refrescarPantalla() {
		ventana.revalidate();
		ventana.repaint();
	}

	/**
	 * Método que devuelve el control del juego de una ventana
	 * 
	 * @return un ControlJuego con el control del juego de la ventana
	 */
	public ControlJuego getJuego() {
		return juego;
	}

	/**
	 * Método para inicializar el programa
	 */
	public void inicializar() {
		// IMPORTANTE, PRIMERO HACEMOS LA VENTANA VISIBLE Y LUEGO INICIALIZAMOS
		// LOS COMPONENTES.
		ventana.setVisible(true);
		inicializarComponentes();
		inicializarListeners();
	}

}
