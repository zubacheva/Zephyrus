<jsp:include page="../WEB-INF/jsphf/header.jsp" />

<link rel="stylesheet" href="/Zephyrus/resources/css/jquery-ui-1.10.4.min.css">
<script src="/Zephyrus/resources/javascript/jquery-ui-1.10.4.min.js"></script>
<script src="/Zephyrus/resources/javascript/accordion.js"></script>

<div class="navigation">
  <a href="Zephyrus/installation/personalTasks" class="current">
   <input type="button" value="My tasks" />
   </a>
   <br>
   <a href="Zephyrus/view/chooseReport" class="current">
   <input type="button" value="Reports" />
   </a>
   <br>
  </div>
  
  
<div class="main">
  <h2> Tasks </h2>
  <div id="actual">
  <c:foreach items="${actualTasks}" var="actualTask">
  <h5>Task ${actualTask.id}</h5>
  <div>
    <ul>
      <li>Task: ${actualOrder.task_value}</li>
    </ul>
    <a href="Zephyrus/takeTask">
    <input type="button" value="Take task">
    </a> 
  </div>
  </c:foreach>

  </div>

  </div>
  
  <jsp:include page="../WEB-INF/jsphf/footer.jsp" />