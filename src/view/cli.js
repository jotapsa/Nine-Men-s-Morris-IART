const Game = require('../logic/game');
// const Board = require('../logic/board.js');
const { Cell } = require('../utils/game_utils.js');

module.exports.cliView = {
  render(model) {
    if (model instanceof Game) {
      const board = model.getBoard();
      const boardSize = board.getBoardSize();
      let boardString = '';
      for (let i = 0; i < boardSize; i += 1) {
        boardString = boardString.concat(`${i + 1} - `);
        for (let j = 0; j < boardSize; j += 1) {
          switch (board.getPos(i, j)) {
            case Cell.BLOCKED:
              boardString = boardString.concat('X');
              break;
            case Cell.OPEN:
              boardString = boardString.concat('O');
              break;
            case Cell.Player0:
              boardString = boardString.concat('R');
              break;
            case Cell.Player1:
              boardString = boardString.concat('B');
              break;
            default:
              break;
          }
        }
        boardString = boardString.concat('\n');
      }
      boardString = boardString.concat('    |||||||\n');
      boardString = boardString.concat('    ABCDEFG');

      console.log(boardString);
    }
  },
};
