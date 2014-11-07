package objetos;

import java.time.LocalTime;
import java.util.*;

public class ViajePuntual extends Viaje {
 private int id;
 private LocalTime fecha;
 public int getId() {
	return id;
}
 private void setId(int id) {
	this.id = id;
}
public LocalTime getFecha() {
	return fecha;
}
public void setFecha(LocalTime fecha) {
	this.fecha = fecha;
}
 
 

}