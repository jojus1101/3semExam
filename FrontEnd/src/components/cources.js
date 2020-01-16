import React, { useState, useEffect } from "react";
import {onlineURL} from "../settings";

const Cources = () => {
  const [hasError, setErrors] = useState(false);
  const [cources, setCourses] = useState({});

  async function fetchData() {
    const res = await fetch(onlineURL);
    res
      .json()
      .then(res => setCourses(res))
      .catch(err => setErrors(err));
  }

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div>
        <h1>Hello </h1>
      <span>Course Name :{JSON.stringify(cources.courseName)}</span>
      <br/>
      <span>Description :{JSON.stringify(cources.description)}</span>
      <hr />
      <span>Has error: {JSON.stringify(hasError)}</span>
    </div>
  );
};
export default Cources;