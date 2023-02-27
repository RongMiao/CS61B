package byog.Core;

public class Player {
    private GameMap map;
    private Pointer currentPosition;

    public Player(GameMap map) {
        this.map = map;
        currentPosition = map.getCurrentPosition();
    }

    public boolean move(char direction) {
        currentPosition = map.getCurrentPosition();
        Pointer nextPosition = new Pointer(currentPosition.x, currentPosition.y);
        switch (direction) {
            case 'w' : {
                nextPosition.y += 1;
                break;
            }
            case 's' : {
                nextPosition.y -= 1;
                break;
            }
            case 'a' : {
                nextPosition.x -= 1;
                break;
            }
            case 'd' : {
                nextPosition.x += 1;
                break;
            }
            default:
                break;
        }
        return map.update(nextPosition);
    }
}
