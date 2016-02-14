// Codigo de cracks: M16B11
// UP: vale 1 |  DOWN: vale 2 | LEFT: vale 3 | RIGHT: vale 4 | BOMB: vale 5
package mouserun.mouse;

import mouserun.game.Grid;
import mouserun.game.Mouse;
import mouserun.game.Cheese;

public class TonyWheel extends Mouse {

    Casilla[][] mapa = new Casilla[100][100];

    /*Constructor para dar nombre al raton*/
    public TonyWheel() {
        super("TonyWheel");
    }

    @Override
    public int move(Grid currentGrid, Cheese cheese) {

        int ratonX = currentGrid.getX();
        int ratonY = currentGrid.getY();
        int quesoX = cheese.getX();
        int quesoY = cheese.getY();
        
       
        
        return Mouse.UP;
    }
    
    @Override
    public void newCheese() {
        mapa = new Casilla[100][100];
    }
    @Override
    public void respawned() {
        mapa = new Casilla[100][100];
    }

    /*Clase casilla heredada de Grid*/
    public class Casilla extends Grid {

        public boolean entrecamino = false, sinsalida=false, nueva=true;
        public int eleccion = 0;

        public Casilla(int x, int y) {
            super(x, y);
            
            //Detectamos si es un cruze de caminos (3 salidas)
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
            
            //Detectamos si es un callejon cerrado
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

}
