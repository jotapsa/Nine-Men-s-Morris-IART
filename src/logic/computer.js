const Player = require('./player.js');

const eval1 = function (state) {
  // TODO: write a board Eval function
  // return boardEval
};

const eval2 = function (state) {
  // TODO: write a board Eval function
  // return boardEval
};


module.exports = class Computer extends Player {
  constructor(depth, evalFunc) {
    super();
    this.depth = depth;
    this.evalFunc = evalFunc;
  }

  alphaBetaAux(state) {
    // return move;
  }

  alphaBeta(state, depth, alpha, beta, maximizerPlayer) {
    if (depth === 0 || state.isGameOver()) {
      return this.evalFunc(state);
    }

    // getPossibleNodes state.getPossibleBoards;

    let value;
    if (maximizerPlayer) {
      value = Number.NEGATIVE_INFINITY;
      // iterate possibleBoards
    } else {
      value = Number.POSITIVE_INFINITY;
    }
  }
};
