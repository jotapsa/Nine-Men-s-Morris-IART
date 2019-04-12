// TODO: when using a browser we need to use this layer to transform the event into something usable by the logic

class GameController {
  GameController(model) {
    this.model = model;
  }

  input(move) {
    console.log(move);
  }
}
