# 276project

Repository for Group 3's CMPT276 maze game aka "DemonGame."
For the ease of everybody, the executable game file demonGame.jar and Javadocs have been created and included in the repository. The demonGame.jar file is located inside 276project/demonGame and the Javadocs are inside 276project/demonGame/target/site directory.

Instructions:

_To use the following maven automations, please ensure that you are calling these commands from the 276project/demonGame directory and have [Apache Maven installed](https://maven.apache.org/install.html)._

-   Clean existing artifacts to create new ones: `mvn clean && del demonGame.jar`.
-   How to Build the game: `mvn package`. This will create an executable demonGame.jar in the 276project/demonGame directory as well as a target folder.
-   How to Run the game: After building the game, run `java -jar demonGame.jar` to run the game.
-   How to Test the game: `mvn test`.
-   How to create documentation (Javadocs): `mvn site` to create HTML files for Java code documentation.

**Note: For the graphics used in the game to load properly, the game should be called from the 276project/demonGame directory.**

---

Gameplay:
![](/Documents/gif/game_demo.gif)

Credits: [Regular reward (sprite)](https://graph.baidu.com/pcpage/similar?originSign=126f485bc504a85850d5401679107792&srcp=crs_pc_similar&tn=pc&idctag=gz&sids=1077595_1080051_1080824_1085874_1085752&gsid=&session_id=17316955970479476862&entrance=general&tpl_from=pc&pageFrom=graph_upload_pcshitu&inspire=general&image=http%3A%2F%2Fmms0.baidu.com%2Fit%2Fu%3D2317220067,749554139%26fm%3D253%26app%3D138%26f%3DPNG%3Fw%3D500%26h%3D500&carousel=503&index=3&page=3&shituToken=c17dbd), [Trap (sprite)](https://graph.baidu.com/pcpage/similar?originSign=126571a9c48e4a5768c7d01679108091&srcp=crs_pc_similar&tn=pc&idctag=gz&sids=1077595_1080051_1080824_1085874_1085752&gsid=&session_id=8006947901671105024&entrance=general&tpl_from=pc&pageFrom=graph_upload_pcshitu&inspire=general&image=http%3A%2F%2Fmms2.baidu.com%2Fit%2Fu%3D2073939964,3501728953%26fm%3D253%26app%3D138%26f%3DJPEG%3Fw%3D300%26h%3D300&carousel=503&index=3&page=8&shituToken=a12e3e), [Bonus reward (sprite)](https://www.bing.com/images/search?view=detailV2&insightstoken=bcid_T.dMyCkT-14FLe7.tqWoL2dmx8zY......4*ccid_90zIKRP7&form=SBIMSN&iss=VSI&sbisrc=ImgDropper&idpbck=1&sbifsz=150+x+150+%c2%b7+3.96+kB+%c2%b7+png&sbifnm=reward2.png&thw=150&thh=150&ptime=19&dlen=5412&expw=150&exph=150&selectedindex=30&id=6ECBCF271F1212054C12DDC548031593198E3977&ccid=90zIKRP7&vt=2&sim=1&pivotparams=insightsToken%3Dbcid_T.dMyCkT-14FqxcxoNWLuD9SqbotqVTdP34), Remaining images/sprites have been taken from royalty free resources.
