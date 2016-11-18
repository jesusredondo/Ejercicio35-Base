import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Clase que implementa el listener de los botones del Buscaminas. De alguna
 * manera tendrá que poder acceder a la ventana principal. Se puede lograr
 * pasando en el constructor la referencia a la ventana. Recuerda que desde la
 * ventana, se puede acceder a la variable de tipo ControlJuego
 * 
 * @author jesusredondogarcia
 **
 */
public class ActionBoton implements ActionListener {

	private VentanaPrincipal ventanaPrincipal;
	private int posicionI, posicionJ;

	public ActionBoton(VentanaPrincipal ventanaPrincipal, int i, int j) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.posicionI = i;
		this.posicionJ = j;
	}

	/**
	 * Acción que ocurrirá cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		ventanaPrincipal.mostrarFinJuego(this.ventanaPrincipal.juego.abrirCasilla(this.posicionI, this.posicionJ));
		if (!this.ventanaPrincipal.juego.esFinJuego()) {
			this.ventanaPrincipal.mostrarNumMinasAlrededor(this.posicionI, this.posicionJ);
			this.ventanaPrincipal.actualizarPuntuacion();
			ventanaPrincipal.juego.depurarTablero();
		} else {
			//muestra el fin del juego si se ha ganado
			ventanaPrincipal.mostrarFinJuego(false);
		}

	}

}
