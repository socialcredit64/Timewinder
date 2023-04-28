public class Room {
    int id;
    boolean z;
    
    public Room(){
        id=-1;
        z=true;
    }

    public Room(int roomid, boolean b){
        id=roomid;
        z=b;
    }

    public void setRoomID(int n){
        id=n;
    }

    public void setZ(boolean b){
        z=b;
    }
}
