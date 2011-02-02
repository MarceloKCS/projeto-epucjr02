/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package epucjr.engyos.test;

/*
This applet will display a gif on screen
for now the gif file must be in the same directory as this applet
*/

import java.awt.*;
import java.applet.*;
// These classes are for Url's.
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageExample extends Applet
{
 // Your image name;
     Image my_gif;

 // The applet base URL
     URL base;

 // This object will allow you to control loading
     MediaTracker mt;

     public void init()
     {
         URL url = null;
        try {
           
            url = new URL(getCodeBase(), "biometria5.jpg");
        } catch (MalformedURLException ex) {
            Logger.getLogger(ImageExample.class.getName()).log(Level.SEVERE, null, ex);
        }
  // initialize the MediaTracker
          mt = new MediaTracker(this);

  // The try-catch is necassary when the URL isn't valid
  // Ofcourse this one is valid, since it is generated by
  // Java itself.

         try {
   // getDocumentbase gets the applet path.
               base = getDocumentBase();
         }
         catch (Exception e) {}

  // Here we load the image.
  // Only Gif and JPG are allowed. Transparant gif also.
          my_gif = getImage(url);

  // tell the MediaTracker to kep an eye on this image, and give it ID 1;
          mt.addImage(my_gif,1);

  // now tell the mediaTracker to stop the applet execution
  // (in this example don't paint) until the images are fully loaded.
  // must be in a try catch block.

         try {
               mt.waitForAll();
          }
          catch (InterruptedException  e) {}

  // when the applet gets here then the images is loaded.

     }

     public void paint(Graphics g)
     {
  // now we are going to draw the gif on the screen
  // (image name,x,y,observer);

          g.drawImage(my_gif,0,0,this);

  // you can resize the image easily

       


     }

}

// That's all. Images can alos be drawn without the mediaTracker but then
// the screen will flicker.
// a base URL variable is also not necessary but when you have lots of images to load
// then it's shorter to type.

// Next is the basic GUI (Grphical User Interface) components.
// go to guiExample.java