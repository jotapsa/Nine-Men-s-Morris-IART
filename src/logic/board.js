const { Cell } = require('../utils/game_utils.js');

module.exports = class Board {
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

    this.boardSize = 7;
  }

  getBoardSize() {
    return this.boardSize;
  }

  getPos(y, x) {
    return this.board[y][x];
  }

  isEmptyPos(y, x) {
    return this.board[y][x] === Cell.OPEN;
  }

  setPos(y, x, cell) {
    this.board[y][x] = cell;
  }
};
