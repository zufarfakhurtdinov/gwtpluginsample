package zufarfakhurtdinov.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class App implements EntryPoint {
  public void onModuleLoad() {
    final Button sendButton = new Button("Send");
    final TextBox nameField = new TextBox();
    nameField.setText("GWT User");
    final Label errorLabel = new Label();


    RootPanel.get("nameFieldContainer").add(nameField);
    RootPanel.get("sendButtonContainer").add(sendButton);
    RootPanel.get("errorLabelContainer").add(errorLabel);

    // We can add style names to widgets
    sendButton.addStyleName("sendButton");

    // Focus the cursor on the name field when the app loads
    nameField.setFocus(true);
    nameField.selectAll();


    sendButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, "/myservlet");
        requestBuilder.setCallback(new RequestCallback() {
          @Override
          public void onResponseReceived(Request request, Response response) {
            errorLabel.setText("response");
          }

          @Override
          public void onError(Request request, Throwable throwable) {
            errorLabel.setText("error:" + throwable);
          }
        });
        requestBuilder.setTimeoutMillis(500);
        try {
          requestBuilder.send();
        } catch (RequestException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
