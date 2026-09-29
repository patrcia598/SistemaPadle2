package reservaPadle;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        // 1. HERRAMIENTAS Y LOGUEO
        Scanner teclado = new Scanner(System.in);

        // Pre-cargamos el empleado de prueba en la lista (id, nombre, apellido, tel, legajo, mail, usuario, contrasenia)
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        listaEmpleados.add(new Empleado(5, "Colon 33", "martina@gmail", "1234", "1234", "Martina", "Lopez", "3535621"));

        Empleado empleadoLogueado = null;

        // Bucle de autenticación con ventanitas simples consecutivas
        while (empleadoLogueado == null) {
            String usuarioIngresado = JOptionPane.showInputDialog(null,
                    "SISTEMA DE TURNOS - PADELAPP\n\nIngrese su Nombre de Usuario:",
                    "Inicio de Sesión",
                    JOptionPane.QUESTION_MESSAGE);

            if (usuarioIngresado == null) {
                JOptionPane.showMessageDialog(null, "Sistema cerrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }

            String contraseniaIngresada = JOptionPane.showInputDialog(null,
                    "SISTEMA DE TURNOS - PADELAPP\n\nIngrese su Contraseña:",
                    "Inicio de Sesión",
                    JOptionPane.QUESTION_MESSAGE);

            if (contraseniaIngresada == null) {
                continue;
            }

            for (Empleado emp : listaEmpleados) {
                if (emp.getUsuario().equals(usuarioIngresado) && emp.getContrasenia().equals(contraseniaIngresada)) {
                    empleadoLogueado = emp;
                    break;
                }
            }

            if (empleadoLogueado != null) {
                JOptionPane.showMessageDialog(null,
                        "¡Bienvenido/a al sistema, " + empleadoLogueado.getNombre() + "!",
                        "Login Exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "❌ Usuario o Contraseña incorrectos. Intente nuevamente.",
                        "Error de Login",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        // =========================================================================
        // 2. DECLARACIÓN DE LISTAS GLOBALES
        // =========================================================================
        ArrayList<Cancha> listaCanchas = new ArrayList<>();
        ArrayList<Turno> grillaTurnos = new ArrayList<>();

        // =========================================================================
        // 3. PROCESO DE PRE-CARGA (RN01, RN10, RN11)
        // =========================================================================
        // Generamos las 6 canchas fijas indicando los 4 parámetros de tu clase Cancha
        for (int i = 1; i <= 6; i++) {
            listaCanchas.add(new Cancha(i, i, false, 8000.0));
        }

        int idTurnoContador = 1;
        // Generamos los turnos de 08:00 a 22:00 para cada cancha
        for (Cancha canchaItem : listaCanchas) {
            for (int hora = 8; hora < 23; hora++) {
                String horaInicio = String.format("%02d:00", hora);

                // Instanciamos el turno correspondiente sin hora fin (5 parámetros)
                // ⚠️ CORREGIDO: Enviamos los 7 parámetros que exige tu constructor real
                Turno nuevoTurno = new Turno(idTurnoContador, idTurnoContador, "2026-10-05", horaInicio, "Disponible", canchaItem, 1);

                grillaTurnos.add(nuevoTurno);
                idTurnoContador++;
            }
        }

        // 4. INSTANCIACIÓN DE DATOS BASE Y CONTROL
        
        Reserva reservaActual = null;
        boolean continuar = true;

        // =========================================================================
        // 5. ENTRADA AL BUCLE DEL MENÚ (while)
        // =========================================================================
        while (continuar) {
            System.out.println("\n==================================================");
            System.out.println("     PANEL DE CONTROL INTERNO - PADELAPP          ");
            System.out.println("==================================================");
            System.out.println(" [1] Cargar Nueva Reserva (Buscar Cliente y Turno)"); // RF06
            System.out.println(" [2] Modificar Turno de una Reserva Activa");        // RF07
            System.out.println(" [3] Imprimir Ticket de Comprobante");               // RF09
            System.out.println(" [4] Cancelar y Liberar una Reserva");               // RF08
            System.out.println(" [5] Dar de Alta un Nuevo Cliente en el Sistema");  // RF01 
            System.out.println(" [6] Cerrar Sesión y Salir");
            System.out.println("==================================================");
            System.out.print("Operación a realizar: ");
            int opcionMenu = teclado.nextInt();

            switch (opcionMenu) {

                case 1: // ---- OPCIÓN 1: MOSTRAR GRILLA Y CREAR RESERVA (RF06) ----
                    // ⚠️ VALIDACIÓN RN08: Verificar si existen clientes registrados
                    if (listaClientes.isEmpty()) {
                        System.out.println("❌ No se puede realizar la reserva. No hay clientes registrados.");
                        System.out.println("Por favor, registre al cliente primero (Opción 1 del panel de altas).");
                        break;
                    }

                    // 1. Mostrar listado de clientes disponibles para que el empleado elija
                    System.out.println("\n=======================================================");
                    System.out.println("               SELECCIONE EL CLIENTE                   ");
                    System.out.println("=======================================================");
                    for (Cliente c : listaClientes) {
                        System.out.println("ID: " + c.getIdCliente() + " | Nombre: " + c.getNombre() + " " + c.getApellido());
                    }
                    System.out.println("=======================================================");
                    System.out.print("Ingrese el ID del cliente que realiza la reserva: ");
                    int idClienteBuscado = teclado.nextInt();

                    // Buscar el objeto cliente en el ArrayList según el ID ingresado
                    Cliente clienteSeleccionado = null;
                    for (Cliente c : listaClientes) {
                        if (c.getIdCliente() == idClienteBuscado) {
                            clienteSeleccionado = c;
                            break;
                        }
                    }

                    // Si el ID no coincide con ninguno, frenamos el proceso
                    if (clienteSeleccionado == null) {
                        System.out.println("❌ El ID de cliente ingresado no existe en el sistema.");
                        break;
                    }
                    // ---- OPCIÓN 1: MOSTRAR GRILLA Y CREAR RESERVA ----
                    for (int i = 0; i < 30; i++) {
                        System.out.println(); // Limpieza visual
                    }
                    System.out.println("=========================================================================");
                    System.out.println("                      GRILLA DE TURNOS DISPONIBLES                       ");
                    System.out.println("=========================================================================");
                    for (int i = 0; i < grillaTurnos.size(); i++) {
                        Turno t = grillaTurnos.get(i);
                        System.out.println("[" + (i + 1) + "] Fecha: " + t.getFecha()
                                + " | Hora: " + t.getHora() + " hs"
                                + " | Cancha: " + t.getCancha().getNumeroCancha()
                                + " | Estado: " + t.getEstado());
                    }
                    System.out.println("=========================================================================");
                    System.out.print("Seleccione el número de turno: ");
                    int elegida = teclado.nextInt() - 1;

                    if (elegida >= 0 && elegida < grillaTurnos.size()) {
                        Turno turnoSeleccionado = grillaTurnos.get(elegida);

                        // Validación de regla de negocio: Evitar duplicados (RF10)
                        if (turnoSeleccionado.getEstado().equalsIgnoreCase("Ocupado")) {
                            System.out.println("❌ Error: Este turno ya está reservado. Elija uno Disponible.");
                        } else {
                            // Si ya tenía una reserva previa, liberamos el turno viejo primero
                            if (reservaActual != null) {
                                reservaActual.getTurno().liberarTurno();
                            }

                            // Instanciamos la nueva reserva (Asocia automáticamente al empleado logueado)
                            reservaActual = new Reserva(501, "2026-09-25", "Confirmada", clienteSeleccionado, empleadoLogueado, turnoSeleccionado.getCancha(), turnoSeleccionado);
                            System.out.println("\n✅ ¡Turno reservado con éxito! Seleccione opción 3 para ver el ticket.");
                        }
                    } else {
                        System.out.println("❌ Opción inválida.");
                    }
                    break;

                case 2: // ---- OPCIÓN 2: CAMBIAR EL TURNO DE LA RESERVA ----
                    if (reservaActual == null) {
                        System.out.println("❌ No hay ninguna reserva creada todavía. Primero elija un turno (Opción 1).");
                    } else {
                        System.out.println("\n--- Seleccione el NUEVO turno para su reserva ---");
                        for (int i = 0; i < grillaTurnos.size(); i++) {
                            Turno t = grillaTurnos.get(i);
                            System.out.println("[" + (i + 1) + "] Fecha: " + t.getFecha()
                                    + " | Hora: " + t.getHora() + " hs"
                                    + " | Cancha: " + t.getCancha().getNumeroCancha()
                                    + " | Estado: " + t.getEstado());
                        }
                        System.out.print("Seleccione el nuevo número de turno: ");
                        int nuevoIndice = teclado.nextInt() - 1;

                        if (nuevoIndice >= 0 && nuevoIndice < grillaTurnos.size()) {
                            Turno nuevoTurno = grillaTurnos.get(nuevoIndice);

                            if (nuevoTurno.getEstado().equalsIgnoreCase("Ocupado")) {
                                System.out.println("❌ Error: El nuevo turno seleccionado ya está ocupado.");
                            } else {
                                // El método setTurno liberará el viejo y ocupará el nuevo de forma automática
                                reservaActual.setTurno(nuevoTurno);
                                reservaActual.setCancha(nuevoTurno.getCancha());
                                System.out.println("\n🔄 ¡El turno de la reserva ha sido modificado con éxito!");
                            }
                        } else {
                            System.out.println("❌ Opción inválida.");
                        }
                    }
                    break;

                case 3: // ---- OPCIÓN 3: EMITIR TICKET ----
                    if (reservaActual == null) {
                        System.out.println("❌ No hay datos para emitir. Debe seleccionar un turno primero.");
                    } else {
                        for (int i = 0; i < 40; i++) {
                            System.out.println(); // Limpieza visual
                        }
                        reservaActual.emitirComprobante();
                    }
                    break;
                case 4: // ---- OPCIÓN 4: CANCELAR RESERVA (RF08) ----
                    if (reservaActual == null) {
                        System.out.println("❌ No hay ninguna reserva activa en el sistema para cancelar.");
                    } else if (reservaActual.getEstado().equalsIgnoreCase("Cancelada")) {
                        System.out.println("❌ Esta reserva ya se encuentra cancelada.");
                    } else {
                        // Guardamos los datos del turno antes de borrarlo para poder mostrarlos en el cartel
                        String horaLiberada = reservaActual.getTurno().getHora();
                        int canchaLiberada = reservaActual.getCancha().getNumeroCancha();

                        // Ejecutamos la cancelación lógica
                        reservaActual.cancelarReserva();

                        System.out.println("\n==================================================");
                        System.out.println("✅ ¡RESERVA CANCELADA CON ÉXITO!");
                        System.out.println("  -> Se liberó la Cancha número: " + canchaLiberada);
                        System.out.println("  -> En el horario de las: " + horaLiberada + " hs");
                        System.out.println("El turno vuelve a estar Disponible para otros jugadores.");
                        System.out.println("==================================================");
                    }
                    break;
                case 5: // ---- DAR DE ALTA UN NUEVO CLIENTE (RF01) ----
                    // 1. Solicitamos los datos de texto al empleado mediante JOptionPane
                    String nombreStr = JOptionPane.showInputDialog(null,
                            "Ingrese el Nombre del cliente:",
                            "Alta de Cliente - PadelApp",
                            JOptionPane.QUESTION_MESSAGE);

                    if (nombreStr == null || nombreStr.trim().isEmpty()) {
                        break;
                    }

                    String apellidoStr = JOptionPane.showInputDialog(null,
                            "Ingrese el Apellido:",
                            "Alta de Cliente - PadelApp",
                            JOptionPane.QUESTION_MESSAGE);

                    if (apellidoStr == null || apellidoStr.trim().isEmpty()) {
                        break;
                    }

                    String telefonoStr = JOptionPane.showInputDialog(null,
                            "Ingrese el Teléfono:",
                            "Alta de Cliente - PadelApp",
                            JOptionPane.QUESTION_MESSAGE);

                    if (telefonoStr == null || telefonoStr.trim().isEmpty()) {
                        break;
                    }

                    // 2. ⚠️ LÓGICA AUTOINCREMENTAL DESDE EL MAIN
                    // Incrementamos el contador estático de la clase Cliente
                    Cliente.setContadorClientes(Cliente.getContadorClientes() + 1);
                    int idAsignado = Cliente.getContadorClientes();

                    // 3. Instanciamos enviando los 4 parámetros exactos que pide tu constructor actual
                    Cliente nuevoCliente = new Cliente(idAsignado, nombreStr, apellidoStr, telefonoStr);

                    // 4. Guardamos en la lista global de clientes
                    listaClientes.add(nuevoCliente);

                    // 5. Cartel informativo para el empleado
                    JOptionPane.showMessageDialog(null,
                            "¡Cliente registrado con éxito por el empleado!\n\n"
                            + "Jugador: " + nuevoCliente.getNombre() + " " + nuevoCliente.getApellido() + "\n"
                            + "ID Asignado por el sistema: " + nuevoCliente.getIdCliente(),
                            "Alta Exitosa",
                            JOptionPane.INFORMATION_MESSAGE);

                    System.out.println("✅ Cliente registrado internamente. ID: " + nuevoCliente.getIdCliente());
                    break;

                case 6: // ---- OPCIÓN 4: SALIR ----
                    System.out.println("👋 Saliendo del sistema de pádel. ¡Buen partido!");
                    continuar = false;
                    break;
                default:
                    System.out.println("❌ Opción de menú inválida. Intente nuevamente.");
            }
        }
        teclado.close();
    }
}
