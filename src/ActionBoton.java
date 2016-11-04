import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Clase que implementa el listener de los botones del Buscaminas.
 * De alguna manera tendrá que poder acceder a la ventana principal.
 * Se puede lograr pasando en el constructor la referencia a la ventana.
 * Recuerda que desde la ventana, se puede acceder a la variable de tipo ControlJuego
 * @author jesusredondogarcia
 *
 */
public class ActionBoton implements ActionListener{

	VentanaPrincipal ventana;
	ControlJuego control;
	int posicionI;
	int posicionJ;

	public ActionBoton(VentanaPrincipal ventana,ControlJuego control,int i,int j) {
		this.ventana= ventana;
		this.control=control;
		this.posicionI=i;
		this.posicionJ=j;
	}
	
	/**
	 *Acción que ocurrirá cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (control.abrirCasilla(posicionI, posicionJ)) {
			ventana.mostrarNumMinasAlrededor(posicionI, posicionJ);
			ventana.actualizarPuntuacion();
			
			}else if(control.esFinJuego()){
				JOptionPane.showMessageDialog(ventana.ventana, "Enhorabuena has ganado", "ENHORABUENA",JOptionPane.INFORMATION_MESSAGE);
				ventana.inhabilitarBotones();
			}else if(control.getMinasAlrededor(posicionI, posicionJ)==-1){
				ventana.mostrarFinJuego(true);
				ventana.inhabilitarBotones();
			}
		
		/*}else if(ventana.mostrarFinJuego()){
			
		}*/
		
		
	}

}
