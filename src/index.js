

// const Game = require('./logic/gameState');
// const GameController = require('./controller/game-controller');
// const GameView = require('./view/cli').cliView;
// const { Cell } = require('./utils/game_utils');
// const Player = require('./logic/player');
// const Computer = require('./logic/computer');

// const { GameState } = require('./utils/game_utils');

// const r1 = readline.createInterface({
//   input: process.stdin,
//   output: process.stdout,
//   prompt: '> ',
// });

// const player0 = new Player(Cell.Player0);
// const player1 = new Player(Cell.Player1);
// const game = new Game(player0, player1);
// const gameController = new GameController(game);

// // console.log('\x1Bc');
// GameView.render(game);
// console.log("What's your move (e.g.: D3) ? ");
// r1.prompt();
// r1.on('line', (input) => {
//   gameController.input(input.trim());
//   // console.log('\x1Bc');
//   GameView.render(game);
//   console.log("What's your move (e.g.: D3) ? ");
//   r1.prompt();
// });

// r1.on('close', () => {
//   console.log('Come again!');
//   process.exit(0);
// });
