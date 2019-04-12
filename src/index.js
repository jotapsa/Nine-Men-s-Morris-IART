const readline = require('readline');

const Game = require('./logic/game.js');
const GameController = require('./controller/game-controller.js');
const GameView = require('./view/cli.js').cliView;

const { GameState } = require('./utils/game_utils.js');

const prompt = readline.createInterface(
  process.stdin,
  process.stdout,
);

const game = new Game();
const gameController = new GameController(game);

GameView.render(game);

prompt.question("What's your move? e.g.: 3D\n", (input) => {
  gameController.input(input);
  GameView.render(game);
  if (game.gameState === GameState.GAME_OVER) {
    prompt.exit();
  }
});
