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

  boardString() {
    let boardString = '';
    this.board.forEach((row) => {
      row.forEach((element) => {
        switch (element) {
          case Cell.BLOCKED:
            boardString = boardString.concat('X');
            break;
          case Cell.OPEN:
            boardString = boardString.concat('O');
            break;
          case Cell.RED:
            boardString = boardString.concat('R');
            break;
          case Cell.BLUE:
            boardString = boardString.concat('B');
            break;
          default:
            break;
        }
      });
      boardString = boardString.concat('\n');
    });
    return boardString;
  }
};
