const { getRandomInt } = require('../utils/utils.js');
const Board = require('../logic/board.js');
const Move = require('../logic/move.js');
const { GameState } = require('../utils/game_utils.js');


module.exports = class Game {
  constructor(player0, player1) {
    this.players = [player0, player1];

    this.reset();
  }

  reset() {
    this.board = new Board();

    this.currentPlayer = this.players[getRandomInt(2)];
    this.turnNo = 0;

    // There are three ways to tie a game
    this.millCountdown = 50; // 50 moves without any mills created
    this.flyingMillCountdown = 10; // 10 moves without any mills created, when both players only have 3 pieces
    // The board is in the exact same configuration three times.
    this.boardHistory = [];

    this.gameState = GameState.PLACING;
  }

  getBoard() {
    return this.board;
  }

  makeMove(move) {
    console.log(move);
    const pos = move.getStartPos();

    switch (this.gameState) {
      case GameState.PLACING:
        if (this.board.isEmptyPos(pos[0], pos[1])) {
          this.board.setPos(pos[0], pos[1], this.currentPlayer.getCell());
        }
        break;
      case GameState.MOVING:
        break;
      default:
        break;
    }
  }
};
