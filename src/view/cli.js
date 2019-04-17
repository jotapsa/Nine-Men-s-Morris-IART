const GameState = require('../logic/gameState');


const boardHelpString = ''
  + '┏━━━┓             ┏━━━┓             ┏━━━┓\n'
  + '┃ 0 ┣━━━━━━━━━━━━━┫ 1 ┣━━━━━━━━━━━━━┫ 2 ┃\n'
  + '┗━┳━┛             ┗━┳━┛             ┗━┳━┛\n'
  + '  ┃   ┏━━━┓       ┏━┻━┓       ┏━━━┓   ┃\n'
  + '  ┃   ┃ 3 ┣━━━━━━━┫ 4 ┣━━━━━━━┫ 5 ┃   ┃\n'
  + '  ┃   ┗━┳━┛       ┗━┳━┛       ┗━┳━┛   ┃\n'
  + '  ┃     ┃   ┏━━━┓ ┏━┻━┓ ┏━━━┓   ┃     ┃\n'
  + '  ┃     ┃   ┃ 6 ┣━┫ 7 ┣━┫ 8 ┃   ┃     ┃\n'
  + '  ┃     ┃   ┗━┳━┛ ┗━━━┛ ┗━┳━┛   ┃     ┃\n'
  + '┏━┻━┓ ┏━┻━┓ ┏━┻━┓       ┏━┻━┓ ┏━┻━┓ ┏━┻━┓\n'
  + '┃ 9 ┣━┫ 10┣━┫ 11┃       ┃ 12┣━┫ 13┣━┫ 14┃\n'
  + '┗━┳━┛ ┗━┳━┛ ┗━┳━┛       ┗━┳━┛ ┗━┳━┛ ┗━┳━┛\n'
  + '  ┃     ┃   ┏━┻━┓ ┏━━━┓ ┏━┻━┓   ┃     ┃\n'
  + '  ┃     ┃   ┃ 15┣━┫ 16┣━┫ 17┃   ┃     ┃\n'
  + '  ┃     ┃   ┗━━━┛ ┗━┳━┛ ┗━━━┛   ┃     ┃\n'
  + '  ┃   ┏━┻━┓       ┏━┻━┓       ┏━┻━┓   ┃\n'
  + '  ┃   ┃ 18┣━━━━━━━┫ 19┣━━━━━━━┫ 20┃   ┃\n'
  + '  ┃   ┗━━━┛       ┗━┳━┛       ┗━━━┛   ┃\n'
  + '┏━┻━┓             ┏━┻━┓             ┏━┻━┓\n'
  + '┃ 21┣━━━━━━━━━━━━━┫ 22┣━━━━━━━━━━━━━┫ 23┃\n'
  + '┗━━━┛             ┗━━━┛             ┗━━━┛\n';

module.exports.cliView = {
  // render(state) {
  //   if (state instanceof GameState) {
  //     // const board = model.getBoard();
  //     const boardString = `${this.getCellString(state.board[0])}--${this.getCellString(state.board[1])}--${this.getCellString(state.board[2])}`;
  //     boardString = boardString.concat('|')
  //     // for (let i = 0; i < boardSize; i += 1) {
  //     //   boardString = boardString.concat(`${i + 1} - `);
  //     //   for (let j = 0; j < boardSize; j += 1) {
  //     //     switch (board.getCell(new Position(j, i))) {
  //     //       case Cell.BLOCKED:
  //     //         boardString = boardString.concat('X');
  //     //         break;
  //     //       case Cell.OPEN:
  //     //         boardString = boardString.concat('O');
  //     //         break;
  //     //       case Cell.Player0:
  //     //         boardString = boardString.concat('R');
  //     //         break;
  //     //       case Cell.Player1:
  //     //         boardString = boardString.concat('B');
  //     //         break;
  //     //       default:
  //     //         break;
  //     //     }
  //     //   }
  //     //   boardString = boardString.concat('\n');
  //     // }


  //     console.log(boardString);
  //   }
  // },

  /* Warning: dangerous string declaration below, js has no printf() */
  render(state) {
    if (state instanceof GameState) {
      const boardElementsArray = [];
      state.board.forEach((element) => {
        boardElementsArray.push(this.getCellString(element));
      });

      const boardString = ''
        + '┏━━━┓             ┏━━━┓             ┏━━━┓    ┏━━━┓             ┏━━━┓             ┏━━━┓\n'
        + `┃ ${boardElementsArray[0]} ┣━━━━━━━━━━━━━┫ ${boardElementsArray[1]} ┣━━━━━━━━━━━━━┫ ${boardElementsArray[2]} ┃    ┃ 0 ┣━━━━━━━━━━━━━┫ 1 ┣━━━━━━━━━━━━━┫ 2 ┃\n`
        + '┗━┳━┛             ┗━┳━┛             ┗━┳━┛    ┗━┳━┛             ┗━┳━┛             ┗━┳━┛\n'
        + '  ┃   ┏━━━┓       ┏━┻━┓       ┏━━━┓   ┃        ┃   ┏━━━┓       ┏━┻━┓       ┏━━━┓   ┃\n'
        + `  ┃   ┃ ${boardElementsArray[3]} ┣━━━━━━━┫ ${boardElementsArray[4]} ┣━━━━━━━┫ ${boardElementsArray[5]} ┃   ┃        ┃   ┃ 3 ┣━━━━━━━┫ 4 ┣━━━━━━━┫ 5 ┃   ┃\n`
        + '  ┃   ┗━┳━┛       ┗━┳━┛       ┗━┳━┛   ┃        ┃   ┗━┳━┛       ┗━┳━┛       ┗━┳━┛   ┃\n'
        + '  ┃     ┃   ┏━━━┓ ┏━┻━┓ ┏━━━┓   ┃     ┃        ┃     ┃   ┏━━━┓ ┏━┻━┓ ┏━━━┓   ┃     ┃\n'
        + `  ┃     ┃   ┃ ${boardElementsArray[6]} ┣━┫ ${boardElementsArray[7]} ┣━┫ ${boardElementsArray[8]} ┃   ┃     ┃        ┃     ┃   ┃ 6 ┣━┫ 7 ┣━┫ 8 ┃   ┃     ┃\n`
        + '  ┃     ┃   ┗━┳━┛ ┗━━━┛ ┗━┳━┛   ┃     ┃        ┃     ┃   ┗━┳━┛ ┗━━━┛ ┗━┳━┛   ┃     ┃\n'
        + '┏━┻━┓ ┏━┻━┓ ┏━┻━┓       ┏━┻━┓ ┏━┻━┓ ┏━┻━┓     ┏━┻━┓ ┏━┻━┓ ┏━┻━┓       ┏━┻━┓ ┏━┻━┓ ┏━┻━┓\n'
        + `┃ ${boardElementsArray[9]} ┣━┫ ${boardElementsArray[10]} ┣━┫ ${boardElementsArray[11]} ┃       ┃ ${boardElementsArray[12]} ┣━┫ ${boardElementsArray[13]} ┣━┫ ${boardElementsArray[14]} ┃     ┃ 9 ┣━┫ 10┣━┫ 11┃       ┃ 12┣━┫ 13┣━┫ 14┃\n`
        + '┗━┳━┛ ┗━┳━┛ ┗━┳━┛       ┗━┳━┛ ┗━┳━┛ ┗━┳━┛     ┗━┳━┛ ┗━┳━┛ ┗━┳━┛       ┗━┳━┛ ┗━┳━┛ ┗━┳━┛\n'
        + '  ┃     ┃   ┏━┻━┓ ┏━━━┓ ┏━┻━┓   ┃     ┃         ┃     ┃   ┏━┻━┓ ┏━━━┓ ┏━┻━┓   ┃     ┃\n'
        + `  ┃     ┃   ┃ ${boardElementsArray[15]} ┣━┫ ${boardElementsArray[16]} ┣━┫ ${boardElementsArray[17]} ┃   ┃     ┃         ┃     ┃   ┃ 15┣━┫ 16┣━┫ 17┃   ┃     ┃\n`
        + '  ┃     ┃   ┗━━━┛ ┗━┳━┛ ┗━━━┛   ┃     ┃         ┃     ┃   ┗━━━┛ ┗━┳━┛ ┗━━━┛   ┃     ┃\n'
        + '  ┃   ┏━┻━┓       ┏━┻━┓       ┏━┻━┓   ┃         ┃   ┏━┻━┓       ┏━┻━┓       ┏━┻━┓   ┃\n'
        + `  ┃   ┃ ${boardElementsArray[18]} ┣━━━━━━━┫ ${boardElementsArray[19]} ┣━━━━━━━┫ ${boardElementsArray[20]} ┃   ┃         ┃   ┃ 18┣━━━━━━━┫ 19┣━━━━━━━┫ 20┃   ┃\n`
        + '  ┃   ┗━━━┛       ┗━┳━┛       ┗━━━┛   ┃         ┃   ┗━━━┛       ┗━┳━┛       ┗━━━┛   ┃\n'
        + '┏━┻━┓             ┏━┻━┓             ┏━┻━┓     ┏━┻━┓             ┏━┻━┓             ┏━┻━┓\n'
        + `┃ ${boardElementsArray[21]} ┣━━━━━━━━━━━━━┫ ${boardElementsArray[22]} ┣━━━━━━━━━━━━━┫ ${boardElementsArray[23]} ┃     ┃ 21┣━━━━━━━━━━━━━┫ 22┣━━━━━━━━━━━━━┫ 23┃\n`
        + '┗━━━┛             ┗━━━┛             ┗━━━┛     ┗━━━┛             ┗━━━┛             ┗━━━┛ \n'
        + '\n'
        + `It's Player${state.currentPlayer} turn.`;

      console.log(boardString);
    }
  },

  getCellString(cell) {
    let string = '';
    switch (cell) {
      case 0:
        string = 'O';
        break;
      case 1:
        string = 'X';
        break;
      case 2:
        string = ' ';
        break;
      default:
        break;
    }

    return string;
  },
};
