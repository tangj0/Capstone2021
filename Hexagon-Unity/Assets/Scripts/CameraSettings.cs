using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraSettings : MonoBehaviour
{
    public static Color backgroundColor = Color.blue;
    public Camera cam;

    // Start is called before the first frame update
    void Start()
    {
        cam = GetComponent<Camera>();
    }

    // Update is called once per frame
    void Update()
    {
        cam.backgroundColor = backgroundColor;
    }

    public static void SetBackgroundColor(Color c)
    {
        backgroundColor = c;
    }
}
