package ohtu;

public class TennisGame {
    
    private int Player1_score = 0;
    private int Player2_score = 0;
    final int ZERO = 0;
    final int ONE = 1;
    final int TWO = 2;
    final int THREE = 3;
    final int FOUR = 4;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            Player1_score += ONE;
        else
            Player2_score += ONE;
    }
    

    public String getScore() {
        String gameSituationAsString = "";
        
        if (Player1_score==Player2_score){
            gameSituationAsString = evenScore();
            }
        
        else if (Player1_score>=FOUR || Player2_score>=FOUR){
            gameSituationAsString = oneAdvantagesOrWins();
        }
        else
        {
            gameSituationAsString = midGame();
            
            }
        
        return gameSituationAsString;
    }
    
    /** Method is called when game is not even and neither one has 40 points.
     * 
     * @return current score as string
     */
    public String midGame(){
        String gameSituationAsString = "";
        int currentPlayersScore=ZERO;
        for (int i=ONE; i<THREE; i++){
                if (i==ONE) currentPlayersScore = Player1_score;
                else { gameSituationAsString+="-"; currentPlayersScore = Player2_score;}
                switch(currentPlayersScore)
                {
                    case ZERO:
                        gameSituationAsString+="Love";
                        break;
                    case ONE:
                        gameSituationAsString+="Fifteen";
                        break;
                    case TWO:
                        gameSituationAsString+="Thirty";
                        break;
                    case THREE:
                        gameSituationAsString+="Forty";
                        break;
                }
        
    }
        return gameSituationAsString;
    }
    /** Method is called when one player is either leading with a 40 score,
     * or when one player wins.
     * 
     * @return current score as string
     */
    public String oneAdvantagesOrWins(){
        String gameSituationAsString = "";
        int situation = Player1_score-Player2_score;
            if (situation==ONE) gameSituationAsString ="Advantage player1";
            else if (situation ==-ONE) gameSituationAsString ="Advantage player2";
            else if (situation>=TWO) gameSituationAsString = "Win for player1";
            else gameSituationAsString ="Win for player2";
        
        return gameSituationAsString;
    }
    /** Method is called when game has an even score.
     * 
     * @return current score as string
     */
    public String evenScore(){
        String gameSituationAsString = "";
        switch (Player1_score)
            {
                case ZERO:
                        gameSituationAsString = "Love-All";
                    break;
                case ONE:
                        gameSituationAsString = "Fifteen-All";
                    break;
                case TWO:
                        gameSituationAsString = "Thirty-All";
                    break;
                case THREE:
                        gameSituationAsString = "Forty-All";
                    break;
                default:
                        gameSituationAsString = "Deuce";
                    break;
                
    }
        return gameSituationAsString;
    }
}