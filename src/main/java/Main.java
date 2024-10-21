import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Main {

    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static SesionDAO sesionDAO = new SesionDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("1. Insertar Usuario y Sesión");
            System.out.println("2. Mostrar Usuarios");
            System.out.println("3. Mostrar Sesiones");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    insertarUsuarioYSesion(scanner);
                    break;
                case 2:
                    mostrarUsuarios();
                    break;
                case 3:
                    mostrarSesiones();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 4);
    }

    private static void insertarUsuarioYSesion(Scanner scanner) {
        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el correo del usuario: ");
        String correo = scanner.nextLine();

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);

        Sesion sesion = new Sesion();
        sesion.setFechaInicioSesion(LocalDateTime.now());
        sesion.setUsuario(usuario);

        usuario.getSesiones().add(sesion);

        usuarioDAO.insertarUsuario(usuario);
        sesionDAO.insertarSesion(sesion);

        System.out.println("Usuario y sesión insertados exitosamente.");
    }

    private static void mostrarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId() + ", Nombre: " + usuario.getNombre() + ", Correo: " + usuario.getCorreo());
        }
    }

    private static void mostrarSesiones() {
        List<Sesion> sesiones = sesionDAO.obtenerSesiones();
        for (Sesion sesion : sesiones) {
            System.out.println("ID Sesión: " + sesion.getId() + ", ID Usuario: " + sesion.getUsuario().getId() + ", Fecha Inicio: " + sesion.getFechaInicioSesion());
        }
    }
}
