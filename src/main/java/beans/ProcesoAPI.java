package beans;

import org.json.JSONObject;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProcesoAPI {

    public static void autocompletarDatos(String dni, UsuarioBean usuario) {
        System.out.println("Autocompletar datos iniciado");
        try {
            String url = "https://apiperu.dev/api/dni"; 
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection(); 
            conn.setRequestMethod("POST"); //solicitud tipo POST 
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer 8b74e5d3b53a26addb48138fe8153afb2ad85b61a67371c4b90e22e05bcd940a");

            conn.setDoOutput(true); //habilita la salida de la conexión
            String jsonInputString = "{\"dni\": \"" + dni + "\"}"; //se prepara la solicitud 
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length); //se envía el cuerpo a la API usando outputStream
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine); //leer 
            }
            in.close();  

            JSONObject json = new JSONObject(content.toString());
            if (json.has("data")) {
                JSONObject data = json.getJSONObject("data"); //procesar respuesta JSON
                usuario.setNombre(data.getString("nombres"));
                usuario.setApellidos(data.getString("apellido_paterno") + " " + data.getString("apellido_materno"));//seteo de dni, nom y apellidos
                FacesMessage msg = new FacesMessage("Datos autocompletados correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println("Datos autocompletados correctamente: " + usuario.getNombre() + " " + usuario.getApellidos());
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se encontraron datos para el DNI proporcionado");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println("No se encontraron datos para el DNI proporcionado");
            }
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo autocompletar los datos");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.out.println("Error al autocompletar datos: " + e.getMessage());
        }
    }
}
