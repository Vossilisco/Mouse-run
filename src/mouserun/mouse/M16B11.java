// Codigo de grupo: M16B11
// UP: vale 1 |  DOWN: vale 2 | LEFT: vale 3 | RIGHT: vale 4 | BOMB: vale 5
package mouserun.mouse;

import java.util.HashMap;
import java.util.Stack;
import mouserun.game.Grid;
import mouserun.game.Mouse;
import mouserun.game.Cheese;

public class M16B11 extends Mouse {

    HashMap<Integer, Grid> mapa;
    HashMap<Integer, Grid> maparecorrido;
    Stack<Integer> memoria;

    /*Constructor para dar nombre al raton*/
    public M16B11() {
        super("TonyWheel");
        mapa = new HashMap<Integer, Grid>();
        maparecorrido = new HashMap<Integer, Grid>();
        memoria = new Stack<Integer>();
    }

    private static int clavemapa(int x, int y) {
        return (x * 10000 + y);
    }

    @Override
    public int move(Grid currentGrid, Cheese cheese) {

        Integer ratonX = currentGrid.getX();
        Integer ratonY = currentGrid.getY();

        /*Si no conoce esta casilla, la guarda en la tabla*/
        if (mapa.get(clavemapa(ratonX, ratonY)) == null) {
            mapa.put(clavemapa(ratonX, ratonY), currentGrid);
        }

        if (maparecorrido.get(clavemapa(ratonX, ratonY)) == null) {
            incExploredGrids();
            maparecorrido.put(clavemapa(ratonX, ratonY), currentGrid);
        }

        if (currentGrid.canGoDown() && (mapa.get(clavemapa(ratonX, ratonY - 1)) == null)) {
            memoria.push(1);
            return Mouse.DOWN;
        }
        if (currentGrid.canGoUp() && mapa.get(clavemapa(ratonX, ratonY + 1)) == null) {
            memoria.push(2);
            return Mouse.UP;
        }

        if (currentGrid.canGoRight() && mapa.get(clavemapa(ratonX + 1, ratonY)) == null) {
            memoria.push(3);
            return Mouse.RIGHT;
        }
        if (currentGrid.canGoLeft() && mapa.get(clavemapa(ratonX - 1, ratonY)) == null) {
            memoria.push(4);
            return Mouse.LEFT;
        }
        if (!memoria.empty()) {
            return memoria.pop();
        } else {
            mapa = new HashMap<Integer, Grid>();
        }
        return Mouse.BOMB;
    }

    @Override
    public void newCheese() {
    }

    @Override
    public void respawned() {
        mapa = new HashMap<Integer, Grid>();
        memoria = new Stack<Integer>();
    }
}
