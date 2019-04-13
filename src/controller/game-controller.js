// eslint-disable-next-line max-len
// TODO: when using a browser we need to use this layer to transform the event into something usable by the logic
const Move = require('../logic/move');
const Board = require('../logic/board');
const Position = require('../logic/position');
const { GameState } = require('../utils/game_utils');

module.exports = class GameController {
  constructor(model) {
    this.model = model;
    this.currentMove = null;
  }

  input(moveInput) {
    // -1 for easier array manipulation.
    const x = moveInput.charCodeAt(0) - 48 - 16 - 1;
    const y = moveInput.charCodeAt(1) - 48 - 1;

    const pos = new Position(x, y);

    if (this.currentMove == null) {
      this.currentMove = new Move(pos);
      switch (this.model.getGameState()) {
        case GameState.PLACING:
          if (this.model.getBoard().isEmptyPos(pos)) {
            const board = new Board(this.model.getBoard().board);
            board.setPos(pos, this.model.getCurrentPlayer().getCell());
            if (board.checkMill(pos)) {
              return;
            }
            this.model.makeMove(this.currentMove);
            // set pos in new board, check mill, if mill is true, then we need optionalPos.
          }
          break;
        default:
          break;
      }
    } else if (this.currentMove != null) {
      switch (this.model.getGameState()) {
        case GameState.PLACING:
          // means we need to check if player has selected oponent piece.
          break;
        default:
          break;
      }
      this.model.makeMove(this.currentMove);
    }
  }
};
