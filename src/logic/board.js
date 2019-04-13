const { Cell } = require('../utils/game_utils.js');

const possibleMills = [
  // horizontal mills
  [[0, 0], [3, 0], [6, 0]],
  [[1, 1], [3, 1], [5, 1]],
  [[2, 2], [3, 2], [4, 2]],
  [[0, 3], [1, 3], [2, 3]],
  [[4, 3], [4, 3], [6, 3]],
  [[2, 3], [3, 4], [4, 4]],
  [[1, 5], [3, 5], [5, 5]],
  [[0, 6], [3, 6], [6, 6]],
  // vertical mills
  [[0, 0], [0, 3], [0, 6]],
  [[1, 1], [1, 3], [1, 5]],
  [[2, 2], [2, 3], [2, 4]],
  [[3, 0], [3, 1], [3, 2]],
  [[3, 4], [3, 5], [3, 6]],
  [[4, 2], [4, 3], [4, 4]],
  [[5, 1], [5, 3], [5, 5]],
  [[6, 0], [6, 3], [6, 6]],
];

module.exports = class Board {
  constructor(board) {
    if (board === undefined) {
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
    } else {
      this.board = board;
    }

    this.boardSize = 7;
  }

  getBoardSize() {
    return this.boardSize;
  }

  getCell(pos) {
    return this.board[pos.getY()][pos.getX()];
  }

  isEmptyPos(pos) {
    return this.board[pos.getY()][pos.getX()] === Cell.OPEN;
  }

  setPos(pos, cell) {
    this.board[pos.getY()][pos.getX()] = cell;
  }

  checkMill(pos) {
    const posArray = [pos.getX(), pos.getY()];
    const mills = [];
    possibleMills.forEach((millArray) => {
      if (millArray.indexOf(posArray) != -1) {
        mills.push(millArray);
      }
    });

    mills.forEach((millArray) => {
      console.log(millArray);
      // TODO: check if elements on board are equal.
      return true;
    });

    return false;
  }
};
