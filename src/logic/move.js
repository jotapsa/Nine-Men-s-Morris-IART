module.exports = class Move {
  constructor(Y, X) {
    this.Y = Y;
    this.X = X;
  }

  getX() {
    return this.X;
  }

  getY() {
    return this.Y;
  }
};
