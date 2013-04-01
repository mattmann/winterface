// Introduced in DOM Level 2:
[NoInterfaceObject]
interface DocumentEvent
{
  // Modified in DOM Level 3:
  Event createEvent(DOMString eventInterface);
};
Document implements DocumentEvent;