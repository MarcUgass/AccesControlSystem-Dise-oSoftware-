package baseNoStates;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;

/*
 * Classe que representa una porta amb estat dinàmic.
 * Utilitza el patró State per gestionar accions com obrir, tancar o bloquejar.
 */
public class Door {
  private final String id;
  private boolean closed;
  //boolean locked;
  private DoorState state; // Estado actual de la puerta lock/unlock
  private Space fromSpace;  // mirar d'on ve la porta
  private Space toSpace;  // mirar a ón va la porta

  public Door(String id, Space fromSpace, Space toSpace) {
    this.id = id;
    closed = true;
    this.state = new Unlocked(this); // Estado inicial
    this.fromSpace = fromSpace;
    this.toSpace = toSpace;

  }

  /*
   * Processa una sol·licitud per a la porta.
   * @param request La sol·licitud que es processarà.
   */
  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        state.open();
        break;
      case Actions.CLOSE:
        state.close();
        break;
      case Actions.LOCK:
        state.lock();
        break;
      case Actions.UNLOCK:
        state.unlock();
        break;
      case Actions.UNLOCK_SHORTLY:
        state.unlock_shortly();
        break;
      default:
        System.out.println("Action " + action + " not implemented.");
    }
  }

  public Area getFromSpace() {
    return fromSpace;
  }

  public Area getToSpace() {
    return toSpace;
  }

  public boolean isClosed() {
    return closed;
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }

  public void setState(DoorState state) {
    this.state = state;
  }

  public String getStateName() {
    return state.getStateName();
  }

  public String getId() {
    return id;
  }


  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }
}