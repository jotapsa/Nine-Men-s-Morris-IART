module.exports = class Move {
  constructor(startPos = null, endPos = null, optionalPos = null) {
    this.startPos = startPos;
    this.endPos = endPos;
    this.optionalPos = optionalPos;
  }

  getStartPos() {
    return this.startPos;
  }

  getEndPos() {
    return this.endPos;
  }

  getOptionalPos() {
    return this.optionalPos;
  }

  // TODO: setters
};
