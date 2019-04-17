const { getRandomInt } = require('../utils/utils.js');
const Move = require('./move');

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

const neighbours = [
  [1, 9],
  [0, 2, 4],
  [1, 14],
  [4, 10],
  [1, 3, 5, 7],
  [4, 13],
  [7, 11],
  [4, 6, 8],
  [7, 12],
  [0, 10, 21],
  [3, 9, 11, 18],
  [6, 10, 15],
  [8, 13, 17],
  [5, 12, 14, 20],
  [2, 13, 23],
  [11, 16],
  [15, 17, 19],
  [12, 16],
  [10, 19],
  [16, 18, 20, 22],
  [13, 19],
  [9, 22],
  [19, 21, 23],
  [14, 22],
];

const nmovesForPlacing = 18;

const player1 = 1;
const player2 = 2;

module.exports = class Game {
  constructor() {
    this.reset();
  }

  reset() {
    this.board = Array(24).fill(0);
    this.currentPlayer = getRandomInt(2) + 1;
    console.log(this.currentPlayer);

    this.noPieces = [9, 9];

    this.nmoves = 0;

    this.millCountdown = 50;
    this.flyingMillCountdown = 10;
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

  nextTurn() {
    this.currentPlayer = 3 - this.currentPlayer;
  }

  isGameOver() {
    // If there are 50 moves without any mills the game ends in a tie.
    // Else if there are 10 completed moves where both player only have 3 pieces.
    // Else if the board repeats itself three times.
    if (this.millCountdown === 0
      || this.flyingMillCountdown === 0
      || this.countBoardRepeats(this.board) === 2) {
      return 0;
    }

    // If there are no possible moves then a player has lost.
    // Else if a player is left with only two pieces he has lost.
    if (getPossibleMoves().length === 0
      || this.noPieces[this.currentPlayer - 1] <= 2) {
      return 3 - this.currentPlayer;
    }

    return -1;
  }

  countBoardRepeats(board) {
    let nrepeats = 0;
    this.boardHistory.forEach((oldBoard) => {
      if (oldBoard === board) {
        nrepeats += 1;
      }
    });
    return nrepeats;
  }

  isInPlacingPhase() {
    return this.nmoves < 18;
  }

  getPossibleMoves() {
    /* Placing phase */
    // existem 24 moves possiveis - aquelas que a sua posicao na board esta a 1
    // verificar se essa move causa um mill, caso sim incluir todas as peças que podem ser retiradas
    /* Moving phase */
    // procurar as peças na board
    // para cada peça ver os vizinhos e se as celulas destes estao a 0
    // verificar se essa move causa um mill, caso sim incluir todas as peças que podem ser retiradas.

    return [];
  }

  checkIfMoveCausesMill(move) {
    // if causes mill
    return true;
    // else return false
  }

  isValidMove(move) {
    return true;
  }

  /* Assume move is validated */
  makeMove(move) {
    console.log(move);
    if (move instanceof Move) {
      if (move.end === null) {
        this.board[move.start] = this.currentPlayer;
      } else {
        this.board[move.start] = 0;
        this.board[move.end] = this.currentPlayer;
      }

      if (move.taken != null) {
        this.board[move.taken] = 0;
        this.noPieces[this.currentPlayer - 1] -= 1;
      }
    }
  }
};
