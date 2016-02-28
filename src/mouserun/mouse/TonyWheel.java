// Codigo de grupo: M16B11
// UP: vale 1 |  DOWN: vale 2 | LEFT: vale 3 | RIGHT: vale 4 | BOMB: vale 5
package mouserun.mouse;

import java.util.HashMap;
import java.util.Stack;
import mouserun.game.Grid;
import mouserun.game.Mouse;
import mouserun.game.Cheese;

public class TonyWheel extends Mouse {

    HashMap<Integer,Grid> mapa;
    Stack<Integer> memoria;

    /*Constructor para dar nombre al raton*/
    public TonyWheel() {
        super("TonyWheel");
        mapa = new HashMap<Integer,Grid>();
        memoria = new Stack<Integer>();
    }
    
    private static int clavemapa(int x, int y) {
        return (x * 10000 + y);
    }

    @Override
    public int move(Grid currentGrid, Cheese cheese) {
        
        Integer ratonX=currentGrid.getX();
        Integer ratonY=currentGrid.getY();
        
        /*Si no conoce esta casilla, la guarda en la tabla*/
        if (mapa.get(clavemapa(ratonX, ratonY))==null){
            incExploredGrids();
            mapa.put(clavemapa(ratonX, ratonY), currentGrid);
        }

        if (currentGrid.canGoDown() && (mapa.get(clavemapa(ratonX, ratonY - 1)) == null)) {
            memoria.push(1);
            return Mouse.DOWN;
        }
        if (currentGrid.canGoUp() && mapa.get(clavemapa(ratonX, ratonY + 1)) == null){
            memoria.push(2);
            return Mouse.UP;
        }    

        if (currentGrid.canGoRight() && mapa.get(clavemapa(ratonX +1, ratonY)) == null) {
            memoria.push(3);
            return Mouse.RIGHT;
        }
        if (currentGrid.canGoLeft() && mapa.get(clavemapa(ratonX -1, ratonY)) == null) {
            memoria.push(4);
            return Mouse.LEFT;
        }
        
    return memoria.pop();
    }
    
    @Override
    public void newCheese() {
        mapa = new HashMap<Integer,Grid>();
        memoria = new Stack<Integer>();
    }
    @Override
    public void respawned() {
    }

    
    //EN CONSTRUCCION
    
    /*Clase casilla heredada de Grid
    public class Casilla extends Grid {

        public boolean entrecamino = false, sinsalida=false, nueva=true;
        public int eleccion = 0;

        public Casilla(int x, int y) {
            super(x, y);
        }
        //Detectamos si es un cruze de caminos (3 salidas)        
        void piensaCruce(){
            //Si hay una sola pared a la IZQUIERDA
            if (this.canGoDown() && this.canGoRight() && this.canGoUp()) {
                entrecamino = true;
            } else{
                //Si hay una sola pared a la ABAJO
                if(this.canGoRight() && this.canGoUp() && this.canGoLeft()) {
                    entrecamino = true;
                }else{
                    //Si hay una sola pared a la DERECHA
                    if (this.canGoUp() && this.canGoLeft() && this.canGoDown()){
                       entrecamino = true; 
                    }else{
                        //Si hay una sola pared a la ARRIBA
                        if (this.canGoLeft() && this.canGoDown() && this.canGoRight()){
                           entrecamino = true; 
                        }else{
                           entrecamino = false;
                        }
                    }
                }
            }
    }
        //Detectamos si es un callejon cerrado
        void piensaSinsalida(){ 
            //Solo se puede ir para la IZQUIERDA
            if(!this.canGoDown() && !this.canGoRight() && !this.canGoUp()) sinsalida=true;
            //Solo se puede ir para la ABAJO
            if(!this.canGoRight() && !this.canGoUp() && !this.canGoLeft()) sinsalida=true;
            //Solo se puede ir para la DERECHA
            if (!this.canGoUp() && !this.canGoLeft() && !this.canGoDown()) sinsalida=true;
            //Solo se puede ir para la ARRIBA
            if (this.canGoLeft() && this.canGoDown() && this.canGoRight()) sinsalida=true;
        }
    }
*/
}
