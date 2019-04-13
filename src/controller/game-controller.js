// eslint-disable-next-line max-len
// TODO: when using a browser we need to use this layer to transform the event into something usable by the logic
const Move = require('../logic/move.js');

module.exports = class GameController {
  constructor(model) {
    this.model = model;
    this.currentMove = new Move();
  }

  input(moveInput) {
    // -1 for easier array manipulation.
    const Y = moveInput.charCodeAt(0) - 48 - 1;
    const X = moveInput.charCodeAt(1) - 48 - 16 - 1;
    const startPos = [Y, X];

    const move = new Move(startPos);

    // TODO: validateMove using Model before passing move to model

    this.model.makeMove(move);
  }
};
