const Move = require('../logic/move.js');
const { Cell, Turn, GameState } = require('../utils/game_utils.js');

module.exports = class Game {
  constructor() {
    // eslint-disable-next-line max-len
    this.board = [
      [Cell.OPEN, Cell.BLOCKED, Cell.BLOCKED, Cell.OPEN, Cell.BLOCKED, Cell.BLOCKED, Cell.OPEN],
      [Cell.BLOCKED, Cell.OPEN, Cell.BLOCKED, Cell.OPEN, Cell.BLOCKED, Cell.OPEN, Cell.BLOCKED],
      [Cell.BLOCKED, Cell.BLOCKED, Cell.OPEN, Cell.OPEN, Cell.OPEN, Cell.BLOCKED, Cell.BLOCKED],
      [Cell.OPEN, Cell.OPEN, Cell.OPEN, Cell.BLOCKED, Cell.OPEN, Cell.OPEN, Cell.OPEN],
      [Cell.BLOCKED, Cell.BLOCKED, Cell.OPEN, Cell.OPEN, Cell.OPEN, Cell.BLOCKED, Cell.BLOCKED],
      [Cell.BLOCKED, Cell.OPEN, Cell.BLOCKED, Cell.OPEN, Cell.BLOCKED, Cell.OPEN, Cell.BLOCKED],
      [Cell.OPEN, Cell.BLOCKED, Cell.BLOCKED, Cell.OPEN, Cell.BLOCKED, Cell.BLOCKED, Cell.OPEN],
    ];

    this.turn = Turn.RED;

    this.gameState = GameState.PLACING;
  }

  makeMove(move) {
    console.log(move);
  }
};
