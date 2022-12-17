package ejercicio3.monitor;

import ejercicio3.models.Muestra;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Servidor {
    private static final int MAX_MUESTRAS = 8;

    // Stack donde se guardan las muestras
    private final Stack<Muestra> muestrasAlmacenadas = new Stack<>();

    // Lock
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition stackEmptyCondition = lock.newCondition();
    private final Condition stackFullCondition = lock.newCondition();

    public Servidor() {
    }

    public void put(Muestra item) throws InterruptedException {
        try {
            lock.lock();
            if (muestrasAlmacenadas.size() == MAX_MUESTRAS) {
                stackFullCondition.await(); // Esperamos, se alcanzo el limite
            }
            muestrasAlmacenadas.push(item);
            System.out.println(item + " almacenado");

            stackEmptyCondition.signalAll(); // Avisamos de que hay espacio
        } finally {
            lock.unlock(); // Liberamos el candado
        }
    }

    public Muestra get() throws InterruptedException {
        try {
            lock.lock();
            if (muestrasAlmacenadas.size() == 0) {
                stackEmptyCondition.await(); // Esperamos, no hay muestras
            }
            // Inicialmente tenia .pop(); pero para este caso he preferido usar firstElement() y removeElementAt()
            Muestra muestra = muestrasAlmacenadas.firstElement();
            muestrasAlmacenadas.removeElementAt(0);
            System.out.println(muestra + " retirada");

            return muestra;
        } finally {
            stackFullCondition.signalAll(); // Avisamos de que hay muestras
            lock.unlock();
        }
    }

}
