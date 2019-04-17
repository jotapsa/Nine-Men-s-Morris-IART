module.exports = class Move {
  constructor(startPos = null, endPos = null, takenPos = null) {
    this.start = startPos;
    this.end = endPos;
    this.taken = takenPos;
  }
};
