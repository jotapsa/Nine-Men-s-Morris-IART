const Game = require('../logic/game');
const { Cell } = require('../utils/game_utils.js');

module.exports.cliView = {
  render(model) {
    if (model instanceof Game) {
      const boardLength = model.board.length;
      let boardString = '';
      for (let i = 0; i < boardLength; i += 1) {
        boardString = boardString.concat(`${boardLength - i} - `);
        for (let j = 0; j < boardLength; j += 1) {
          switch (model.board[i][j]) {
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
        }
        boardString = boardString.concat('\n');
      }
      boardString = boardString.concat('    |||||||\n');
      boardString = boardString.concat('    ABCDEFG');

      console.log(boardString);
    }
  },
};
