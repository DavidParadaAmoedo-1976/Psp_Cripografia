import java.security.Security;
import java.security.Provider;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase utilitaria para obtener los algoritmos hash (MessageDigest)
 * disponibles en la JVM a través de los proveedores de seguridad.
 */
public class AlgoritmosHash {

    /**
     * Obtiene los algoritmos hash disponibles en la JVM.
     *
     * @return Conjunto de nombres de algoritmos hash sin duplicados.
     */
    public static Set<String> obtenerAlgoritmosHashDisponibles() {

        // Conjunto para evitar duplicados
        Set<String> algoritmos = new HashSet<String>();

        // Obtener proveedores de seguridad
        Provider[] proveedores = Security.getProviders();

        // Recorrer proveedores y sus servicios
        for (int i = 0; i < proveedores.length; i++) {
            Provider proveedor = proveedores[i];

            for (Provider.Service servicio : proveedor.getServices()) {
                if ("MessageDigest".equalsIgnoreCase(servicio.getType())) {
                    algoritmos.add(servicio.getAlgorithm());
                }
            }
        }

        return algoritmos;
    }

    /**
     * Método principal de prueba.
     */
    public static void main(String[] args) {

        Set<String> hashes = obtenerAlgoritmosHashDisponibles();

        System.out.println("Algoritmos hash disponibles en la JVM:");

        // Foreach normal sobre el Set
        for (String hash : hashes) {
            System.out.println(hash);
        }
    }
}

