// eslint-disable-next-line max-len
// TODO: when using a browser we need to use this layer to transform the event into something usable by the logic
const Move = require('../logic/move.js');
const { MoveType } = require('../utils/game_utils.js');

module.exports = class GameController {
  constructor(model) {
    this.model = model;
  }

  input(moveInput) {
    console.log(`line - ${moveInput[0]}`);
    console.log(`column - ${moveInput[1]}`);

    // TODO: convert to int -> create Move object -> validateMove using Model -> pass move to model
    // const move = new Move(MoveType.PLACING, );
  }
};
