const { getRandomInt } = require('../utils/utils.js');
const Board = require('../logic/board.js');
const Move = require('./move.js');
const { GameState } = require('../utils/game_utils.js');

const possibleMills = [
  [0, 1, 2],
  [3, 4, 5],
  [6, 7, 8],
  [9, 10, 11],
  [12, 13, 14],
  [15, 16, 17],
  [18, 19, 20],
  [21, 22, 23],
  [0, 9, 21],
  [3, 10, 18],
  [6, 11, 15],
  [1, 4, 7],
  [16, 19, 22],
  [8, 12, 17],
  [5, 13, 20],
  [2, 14, 23],
];

const nmovesForPlacing = 18;

module.exports = class Game {
  constructor(player0, player1) {
    this.players = [player0, player1];

    this.reset();
  }

  reset() {
    this.board = Array(24).fill(0);

    this.currentPlayer = this.players[getRandomInt(2)];
    this.turnNo = 0;

    // There are three ways to tie a game
    this.millCountdown = 50; // 50 moves without any mills created
    this.flyingMillCountdown = 10; // 10 moves without any mills created, when both players only have 3 pieces
    // The board is in the exact same configuration three times.
    this.boardHistory = [];
  }

  getBoard() {
    return this.board;
  }

  getGameState() {
    return this.gameState;
  }

  getCurrentPlayer() {
    return this.currentPlayer;
  }

  isGameOver() {
    /* tie return 0 */
    // 50 moves without mills (millcountdown)
    // 10 moves where both players only have 3 pieces (flying)
    // if the board repeats three times

    /* win return playerNumber */
    // a player has no possible moves then he loses.
    // a player loses if he is left with two pieces.

    return -1;
  }

  getPossibleMoves() {
    /* Placing phase */
    // existem 24 moves possiveis - aquelas que a sua posicao na board esta a 1
    // verificar se essa move causa um mill, caso sim incluir todas as peças que podem ser retiradas

    /* Moving phase */
    // procurar as peças na board
    // para cada peça ver os vizinhos e se as celulas destes estao a 0
    // verificar se essa move causa um mill, caso sim incluir todas as peças que podem ser retiradas.
  }

  checkIfMoveCausesMill(move) {
    // if causes mill
    return true;
    // else return false
  }

  /* Assume move is validated */

  makeMove(move) {
    console.log(move);
    const pos = move.getStartPos();

    if (/* move.to == null */ true) {
      // placing
    } else {
      // moving
    }

    if (/* move.taken != null */ true) {
      // remove piece
      // update piece counts
    }
  }
};
